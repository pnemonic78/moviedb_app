package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class Media {
    @SerialName("adult")
    var adult: Boolean = false

    @SerialName("id")
    var id: Long = 0

    @SerialName("media_type")
    var mediaType: MediaType = MediaType.all

    @SerialName("popularity")
    var popularity: Double = 0.0
}