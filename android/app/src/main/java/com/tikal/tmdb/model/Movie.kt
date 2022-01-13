package com.tikal.tmdb.model

import com.tikal.tmdb.json.DateCalendarSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Calendar

/**
 * Movie POJO.
 */
@Serializable
data class Movie(
    @SerialName("id") var id: Long,
    @SerialName("adult") var adult: Boolean,
    @SerialName("backdrop_path") var backdropPath: String?,
    @SerialName("genre_ids") var genreIds: List<Long>,
    @SerialName("original_language") var originalLanguage: String,
    @SerialName("original_title") var originalTitle: String,
    @SerialName("overview") var overview: String?,
    @SerialName("popularity") var popularity: Float,
    @SerialName("poster_path") var posterPath: String?,
    @SerialName("release_date")
    @Serializable(with = DateCalendarSerializer::class)
    var releaseDate: Calendar?,
    @SerialName("title") var title: String,
    @SerialName("video") var video: Boolean,
    @SerialName("vote_average") var voteAverage: Float,
    @SerialName("vote_count") var voteCount: Int
)