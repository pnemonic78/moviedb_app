package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1") var id: String,
    @SerialName("name") var name: String
)