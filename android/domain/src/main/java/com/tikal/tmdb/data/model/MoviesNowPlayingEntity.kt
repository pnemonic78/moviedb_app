package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Response for Movies that are Now Playing.
 */
@Entity(tableName = "movies_now_playing")
data class MoviesNowPlayingEntity(
//    @ColumnInfo(name = "results")
    @Ignore
    var results: List<MovieEntity>,
//    @ColumnInfo(name = "dates")
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
    constructor(
        dates: DatesEntity,
        page: Int,
        totalPages: Int,
        totalResult: Int
    ) : this(
        results = emptyList(),
        dates = dates,
        page = page,
        totalPages = totalPages,
        totalResult = totalResult
    )
}