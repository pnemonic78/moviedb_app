package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.DateTime
import com.tikal.tmdb.json.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Television : Media() {
    init {
        mediaType = MediaType.tv
    }

    @SerialName("episode_count")
    var episodeCount: Int = 0

    @SerialName("first_air_date")
    @Serializable(with = DateTimeSerializer::class)
    var firstAirDate: DateTime? = null
}
