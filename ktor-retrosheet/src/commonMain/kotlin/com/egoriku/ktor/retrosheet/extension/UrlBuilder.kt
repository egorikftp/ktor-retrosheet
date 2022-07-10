package com.egoriku.ktor.retrosheet.extension

import io.ktor.http.*

private const val URL_START = "https://docs.google.com/spreadsheets"

internal val URLBuilder.iGoogleSheetUrl: Boolean
    get() = buildString().startsWith(URL_START)

internal fun Url.iGoogleSheetUrl(): Boolean = toString().startsWith(URL_START)