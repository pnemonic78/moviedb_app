package com.tikal.tmdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tikal.tmdb.json.DateCalendarSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Calendar

/**
 * Movie POJO.
 */
@Serializable
@Entity(tableName = "movie")
open class Movie(
    @SerialName("id")
    @PrimaryKey
    open val id: Long,
    @SerialName("adult")
    open val adult: Boolean,
    @SerialName("backdrop_path")
    open val backdropPath: String? = null,
    @SerialName("genre_ids")
    open val genreIds: LongArray = longArrayOf(),
    @SerialName("original_language")
    open val originalLanguage: String,
    @SerialName("original_title")
    open val originalTitle: String,
    @SerialName("overview")
    open val overview: String?,
    @SerialName("popularity")
    open val popularity: Float,
    @SerialName("poster_path")
    open val posterPath: String? = null,
    @Serializable(with = DateCalendarSerializer::class)
    @SerialName("release_date")
    open val releaseDate: Calendar? = null,
    @SerialName("title")
    open val title: String,
    @SerialName("video")
    open val video: Boolean = false,
    @SerialName("vote_average")
    open val voteAverage: Float,
    @SerialName("vote_count")
    open val voteCount: Int
)