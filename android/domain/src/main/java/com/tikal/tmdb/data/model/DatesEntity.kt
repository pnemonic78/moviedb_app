package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import java.util.Calendar

/**
 * Dates.
 */
data class DatesEntity(
    @ColumnInfo(name = "maximum")
    val maximum: Calendar? = null,
    @ColumnInfo(name = "minimum")
    val minimum: Calendar? = null
)