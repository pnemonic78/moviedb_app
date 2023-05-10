package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("media")
abstract class MediaEntity {
    @ColumnInfo("adult")
    var adult: Boolean = false

    @ColumnInfo("id")
    @PrimaryKey
    var id: Long = 0

    @ColumnInfo("media_type")
    var mediaType: MediaType = MediaType.all

    @ColumnInfo("popularity")
    var popularity: Double = 0.0
}