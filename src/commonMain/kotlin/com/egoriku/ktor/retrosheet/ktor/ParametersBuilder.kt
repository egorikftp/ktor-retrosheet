package com.egoriku.ktor.retrosheet.ktor

import io.ktor.http.*

fun ParametersBuilder.replace(key: String, newValue: String) {
    remove(key)
    append(key, newValue)
}