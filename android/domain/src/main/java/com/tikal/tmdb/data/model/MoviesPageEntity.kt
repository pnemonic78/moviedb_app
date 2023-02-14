package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@Entity(tableName = "movies_page")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class MoviesPageEntity(
    @Embedded(prefix = "dates_")
    val dates: DatesEntity,
    @PrimaryKey
    var id: Long,
    @ColumnInfo(name = "page")
    private var _page: Int,
    @ColumnInfo(name = "total_pages")
    val totalPages: Int,
    @ColumnInfo(name = "total_results")
    val totalResult: Int,
    @ColumnInfo(name = "type")
    private var _type: MoviesPageType
) {

    constructor(
        dates: DatesEntity,
        page: Int,
        totalPages: Int,
        totalResult: Int,
        type: MoviesPageType
    ) : this(
        dates = dates,
        id = 0L,
        _page = page,
        totalPages = totalPages,
        totalResult = totalResult,
        _type = type
    )

    init {
        updateId()
    }

    var page: Int
        get() = _page
        set(value) {
            _page = value
            updateId()
        }

    var type: MoviesPageType
        get() = _type
        set(value) {
            _type = value
            updateId()
        }

    private fun updateId() {
        id = (type.ordinal.toLong() shl 32) + page
    }
}