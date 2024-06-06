package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("id")
    val id: Long,

    @SerialName("backdrops")
    val backdrops: List<MediaImage> = emptyList(),

    @SerialName("posters")
    val posters: List<MediaImage> = emptyList()
)
