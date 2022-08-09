package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

/**
 * Dates.
 */
//@Entity(tableName = "dates")
data class DatesEntity(
//    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "maximum")
    val maximum: Calendar?,
    @ColumnInfo(name = "minimum")
    val minimum: Calendar?
)