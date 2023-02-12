package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

/**
 * Response for Movies that are Now Playing.
 */
@Entity(tableName = "movies_now_playing")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class MoviesNowPlayingEntity(
//    @ColumnInfo(name = "results")
    @Ignore
    var results: List<MovieEntity>,
    @Embedded(prefix = "dates_")
    var dates: DatesEntity,
    @ColumnInfo(name = "page")
    @PrimaryKey
    var page: Int,
    @ColumnInfo(name = "total_pages")
    var totalPages: Int,
    @ColumnInfo(name = "total_results")
    var totalResult: Int
) {
    @SuppressWarnings("unused")
    // Default constructor for room.
    constructor() : this(
        results = emptyList(),
        dates = DatesEntity(),
        page = 0,
        totalPages = 0,
        totalResult = 0
    )
}