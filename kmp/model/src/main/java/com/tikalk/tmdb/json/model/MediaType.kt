package com.tikalk.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MediaType {
    @SerialName("all")
    all,

    @SerialName("movie")
    movie,

    @SerialName("tv")
    tv,

    @SerialName("person")
    person
}
