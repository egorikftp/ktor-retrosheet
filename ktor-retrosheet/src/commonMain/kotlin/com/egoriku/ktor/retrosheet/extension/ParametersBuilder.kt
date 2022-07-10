package com.egoriku.ktor.retrosheet.extension

import io.ktor.http.*

internal fun ParametersBuilder.replace(key: String, newValue: String) {
    remove(key)
    append(key, newValue)
}