package com.tikalk.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("results")
    val results: List<Video>
)
