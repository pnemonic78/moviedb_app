package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.DateTime
import com.tikal.tmdb.json.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Television : Media() {
    @SerialName("adult")
    override var adult: Boolean = false

    @SerialName("id")
    override var id: Long = 0

    @SerialName("media_type")
    override var mediaType: MediaType = MediaType.tv

    @SerialName("popularity")
    override var popularity: Double = 0.0

    @SerialName("episode_count")
    var episodeCount: Int = 0

    @SerialName("first_air_date")
    @Serializable(with = DateTimeSerializer::class)
    var firstAirDate: DateTime? = null
}
