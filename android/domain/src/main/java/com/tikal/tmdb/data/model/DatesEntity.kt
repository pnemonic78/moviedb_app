package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import com.tikal.tmdb.domain.DateTime

/**
 * Dates.
 */
data class DatesEntity(
    @ColumnInfo(name = "maximum")
    val maximum: DateTime? = null,
    @ColumnInfo(name = "minimum")
    val minimum: DateTime? = null
)