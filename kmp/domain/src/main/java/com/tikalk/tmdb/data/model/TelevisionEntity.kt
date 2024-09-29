package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.tikalk.tmdb.json.DateTime

@Entity(tableName = "tv")
class TelevisionEntity : MediaEntity() {
    init {
        mediaType = MediaType.tv
    }

    @ColumnInfo("episode_count")
    var episodeCount: Int = 0

    @ColumnInfo("first_air_date")
    var firstAirDate: DateTime? = null
}
