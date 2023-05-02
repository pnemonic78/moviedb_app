package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.DateTimeSerializer
import com.tikal.tmdb.json.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Dates.
 */
@Serializable
data class Dates(
    @SerialName("maximum")
    @Serializable(with = DateTimeSerializer::class)
    val maximum: DateTime?,

    @SerialName("minimum")
    @Serializable(with = DateTimeSerializer::class)
    val minimum: DateTime?
)