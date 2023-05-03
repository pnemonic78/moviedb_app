package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesResponse(
    @SerialName("id")
    val id: Long,

    @SerialName("backdrops")
    val backdrops: List<MovieImage> = emptyList(),

    @SerialName("posters")
    val posters: List<MovieImage> = emptyList()
)
