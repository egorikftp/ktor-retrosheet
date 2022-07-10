package com.egoriku.ktor.retrosheet.example.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable

data class Features(
    @SerialName("feature_name")
    val featureName: String,

    @SerialName("sheet_name")
    val sheetName: String,

    @SerialName("icon_url")
    val iconUrl: String,

    @SerialName("is_available")
    val isAvailable: Boolean
)