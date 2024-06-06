package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VideoType {
    @SerialName("Trailer")
    trailer,

    @SerialName("Teaser")
    teaser,

    @SerialName("Clip")
    clip,

    @SerialName("Featurette")
    featurette,

    @SerialName("Behind the Scenes")
    behindTheScenes,

    @SerialName("Bloopers")
    bloopers
}
