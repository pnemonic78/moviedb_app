package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @ColumnInfo(name = "adult")
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String
)