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
    @SerialName("id") val id: Long,
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("genre_ids") val genreIds: List<Long>,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Float,
    @SerialName("poster_path") val posterPath: String?,
    @Serializable(with = DateCalendarSerializer::class)
    @SerialName("release_date") val releaseDate: Calendar?,
    @SerialName("title") val title: String,
    @SerialName("video") val video: Boolean,
    @SerialName("vote_average") val voteAverage: Float,
    @SerialName("vote_count") val voteCount: Int
)