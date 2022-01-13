package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguage(
    @SerialName("iso_639_1") var id: String,
    @SerialName("name") var name: String
)