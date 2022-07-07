package com.egoriku.ktor.retrosheet

expect fun parseCsv(data: String): List<Map<String, String>>