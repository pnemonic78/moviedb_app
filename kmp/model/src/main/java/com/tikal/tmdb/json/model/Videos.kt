package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Videos(
    @SerialName("id")
    val id: Long,
    @SerialName("results")
    val results: List<Video>
)
