package com.egoriku.ktor.retrosheet.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Count(
    @SerialName("count category_id")
    val count: Int
)