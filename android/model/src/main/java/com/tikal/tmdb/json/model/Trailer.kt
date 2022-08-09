package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Trailer POJO.
 */
@Serializable
data class Trailer(
    @SerialName("id")
    val id: String
)