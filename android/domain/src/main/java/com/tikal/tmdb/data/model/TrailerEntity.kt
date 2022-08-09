package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Trailer entity.
 */
@Entity(tableName = "trailer")
data class TrailerEntity(
    @ColumnInfo(name="id")
    @PrimaryKey
    val id: String
)