package com.egoriku.ktor.retrosheet.example

import com.egoriku.ktor.retrosheet.example.model.Config
import com.egoriku.ktor.retrosheet.example.model.Count
import com.egoriku.ktor.retrosheet.example.model.Features
import com.egoriku.ktor.retrosheet.extension.defaultSheetUrl
import com.egoriku.ktor.retrosheet.extension.query
import com.egoriku.ktor.retrosheet.extension.sheet
import com.egoriku.ktor.retrosheet.plugin.RetrosheetPlugin
import com.egoriku.ktor.retrosheet.plugin.configuration
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking

const val FEATURES = "features"
const val CATEGORIES = "categories"

fun main() {
    runBlocking {
        val httpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                defaultSheetUrl(docId = "1XrYNW2hX4lMxMhd8rOFzk5vrjYlxg3WxEKPAqDMhB54")
            }
            install(RetrosheetPlugin) {
                configuration = configuration {
                    sheet {
                        name = CATEGORIES
                        columns = listOf(
                            "category_id",
                            "category_name",
                            "subcategory_id",
                            "subcategory_name",
                            "description"
                        )
                    }
                    sheet {
                        name = FEATURES
                        columns = listOf(
                            "feature_name",
                            "sheet_name",
                            "icon_url",
                            "subcategory_name",
                            "is_available"
                        )
                    }
                }
            }
        }

        httpClient.get {
            sheet(CATEGORIES)
        }.body<List<Config>>().also {
            println(it.joinToString(separator = "\n"))
            println("__________")
        }

        httpClient.get {
            sheet(CATEGORIES)
            query("WHERE category_id = 1")
        }.body<List<Config>>().also {
            println(it.joinToString(separator = "\n"))
            println("__________")
        }

        httpClient.get {
            sheet(CATEGORIES)
            query("SELECT COUNT(category_id)")
        }.body<Count>().also {
            println(it)
            println("__________")
        }

        httpClient.get {
            sheet(FEATURES)
        }.body<List<Features>>().also {
            println(it.joinToString(separator = "\n"))
            println("__________")
        }
    }
}
