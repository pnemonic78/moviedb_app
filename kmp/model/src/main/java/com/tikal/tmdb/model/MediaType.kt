package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MediaType {
    @SerialName("all")
    All,

    @SerialName("movie")
    Movie,

    @SerialName("tv")
    TV,

    @SerialName("person")
    Person
}
