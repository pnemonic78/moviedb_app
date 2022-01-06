package com.tikal.tmdb.model

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * Dates.
 */
class Dates(
    @SerializedName("maximum") val maximum: Date,
    @SerializedName("minimum") val minimum: Date
)