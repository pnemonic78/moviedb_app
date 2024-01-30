package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VideoType {
    @SerialName("Trailer")
    Trailer,

    @SerialName("Teaser")
    Teaser,

    @SerialName("Clip")
    Clip,

    @SerialName("Featurette")
    Featurette,

    @SerialName("Behind the Scenes")
    BehindTheScenes,

    @SerialName("Bloopers")
    Bloopers
}
