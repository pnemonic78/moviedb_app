package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Locale

@Entity("video")
data class VideoEntity(
    @ColumnInfo("id")
    @PrimaryKey
    val id: String,
    @ColumnInfo("locale")
    val locale: Locale? = null,
    @ColumnInfo("key")
    val key: String?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("site")
    val site: String?,
    @ColumnInfo("size")
    val size: Int = 0,
    @ColumnInfo("type")
    val type: VideoType?
)