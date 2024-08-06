package com.tikalk.tmdb.json.model

import com.tikalk.tmdb.json.DateTime
import com.tikalk.tmdb.json.DateTimeSerializer
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