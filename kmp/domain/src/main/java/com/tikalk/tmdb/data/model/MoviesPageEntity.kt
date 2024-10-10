package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@Entity(tableName = "movies_page")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
class MoviesPageEntity(
    @Embedded(prefix = "dates_")
    val dates: DatesEntity?,
    @PrimaryKey
    @ColumnInfo("id")
    var id: Long = 0L,
    page: Int,
    @ColumnInfo(name = "total_pages")
    val totalPages: Int,
    @ColumnInfo(name = "total_results")
    val totalResults: Int,
    type: MoviesPageType
) {

    @Ignore
    private val idMutable = id == 0L

    @ColumnInfo(name = "page")
    var page: Int = page
        set(value) {
            field = value
            updateId()
        }

    @ColumnInfo(name = "type")
    var type: MoviesPageType = type
        set(value) {
            field = value
            updateId()
        }

    init {
        updateId()
    }

    private fun updateId() {
        if (idMutable) {
            id = (type.ordinal.toLong() shl 32) + page
        }
    }
}