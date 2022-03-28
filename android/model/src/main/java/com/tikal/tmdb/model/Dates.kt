package com.tikal.tmdb.model

import com.tikal.tmdb.json.DateCalendarSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Calendar

/**
 * Dates.
 */
@Serializable
class Dates(
    @SerialName("maximum")
    @Serializable(with = DateCalendarSerializer::class)
    val maximum: Calendar?,
    @SerialName("minimum")
    @Serializable(with = DateCalendarSerializer::class)
    val minimum: Calendar?
)