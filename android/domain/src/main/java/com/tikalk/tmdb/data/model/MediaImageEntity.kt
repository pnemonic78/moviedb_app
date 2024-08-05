package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Locale

@Entity("media_image")
data class MediaImageEntity(
    @ColumnInfo("id")
    @PrimaryKey
    val id: String,
    @ColumnInfo("locale")
    val locale: Locale? = null,
    @ColumnInfo("width")
    val width: Int,
    @ColumnInfo("height")
    val height: Int,
    @ColumnInfo("aspect_ratio")
    val aspectRatio: Double = 1.0,
    @ColumnInfo("file_path")
    val path: String,
    @ColumnInfo("vote_average")
    val voteAverage: Double = 0.0,
    @ColumnInfo("vote_count")
    val voteCount: Int = 0
)