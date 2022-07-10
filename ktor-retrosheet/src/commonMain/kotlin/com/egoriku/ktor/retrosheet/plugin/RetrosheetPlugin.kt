package com.egoriku.ktor.retrosheet.plugin

import com.egoriku.ktor.retrosheet.extension.iGoogleSheetUrl
import com.egoriku.ktor.retrosheet.extension.replace
import com.egoriku.ktor.retrosheet.util.QueryConverter
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.*
import kotlinx.serialization.csv.Csv
import kotlinx.serialization.serializer

class RetrosheetPlugin(private val sheets: Map<String, Map<String, String>>) {

    class Config {
        var configuration: Configuration = Configuration(sheets = emptyMap())
    }

    companion object Feature : HttpClientPlugin<Config, RetrosheetPlugin> {

        private val csv = Csv {
            ignoreUnknownColumns = true
            hasHeaderRecord = true
        }

        override val key: AttributeKey<RetrosheetPlugin> = AttributeKey("retrosheet-kmm")

        override fun prepare(block: Config.() -> Unit): RetrosheetPlugin {
            val config = Config().apply(block)

            return RetrosheetPlugin(sheets = config.configuration.sheets)
        }

        override fun install(plugin: RetrosheetPlugin, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                val urlBuilder = context.url

                when {
                    urlBuilder.iGoogleSheetUrl -> {
                        context.url {
                            encodedPath = encodedPath.plus("/gviz/tq")

                            parameters.append("tqx", "out:csv")

                            val sheetName = requireNotNull(urlBuilder.parameters["sheet"])

                            val query = urlBuilder.parameters["tq"]

                            if (!query.isNullOrEmpty()) {
                                val escapedQuery = QueryConverter(
                                    smartQuery = query,
                                    smartQueryMap = requireNotNull(plugin.sheets[sheetName]) {
                                        "Couldn't find queryMap for '$sheetName'"
                                    },
                                ).convert()

                                parameters.replace("tq", escapedQuery)
                            }
                        }
                        proceedWith(it)
                    }
                    else -> return@intercept
                }
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.Receive) { (info: TypeInfo, body: Any) ->
                if (!context.request.url.iGoogleSheetUrl()) return@intercept
                if (body !is ByteReadChannel) return@intercept
                val kotlinType = info.kotlinType ?: return@intercept

                val response = csv.decodeFromString(
                    deserializer = serializer(kotlinType),
                    string = body.readRemaining().readText()
                ) ?: return@intercept

                proceedWith(
                    HttpResponseContainer(
                        expectedType = info,
                        response = response
                    )
                )
            }
        }
    }
}