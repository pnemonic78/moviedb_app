package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguage(
    @SerialName("iso_639_1")
    val id: String,

    @SerialName("name")
    val name: String
)