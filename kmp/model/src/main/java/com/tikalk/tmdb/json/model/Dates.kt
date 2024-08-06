package com.tikalk.tmdb.json.model

import com.tikalk.tmdb.json.DateTimeSerializer
import com.tikalk.tmdb.json.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Dates.
 */
@Serializable
data class Dates(
    @SerialName("maximum")
    @Serializable(with = com.tikalk.tmdb.json.DateTimeSerializer::class)
    val maximum: com.tikalk.tmdb.json.DateTime?,

    @SerialName("minimum")
    @Serializable(with = com.tikalk.tmdb.json.DateTimeSerializer::class)
    val minimum: com.tikalk.tmdb.json.DateTime?
)