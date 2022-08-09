package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.DateCalendarSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Calendar

/**
 * Movie POJO.
 */
@Serializable
data class Movie(
    @SerialName("id")
    val id: Long,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("genre_ids")
    val genreIds: LongArray = longArrayOf(),

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("overview")
    val overview: String?,

    @SerialName("popularity")
    val popularity: Float,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @Serializable(with = DateCalendarSerializer::class)
    @SerialName("release_date")
    val releaseDate: Calendar? = null,

    @SerialName("title")
    val title: String,

    @SerialName("video")
    val video: Boolean = false,

    @SerialName("vote_average")
    val voteAverage: Float,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("budget")
    val budget: Int = 0,

    //TODO @SerialName("credits")
    //TODO val credits: CreditsResponse? = null,

    @SerialName("genres")
    val genres: List<Genre>? = null,

    @SerialName("homepage")
    val homepage: String? = null,

    @SerialName("imdb_id")
    val imdbId: String? = null,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,

    @SerialName("revenue")
    val revenue: Int? = null,

    @SerialName("runtime")
    val runtime: Int? = null,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,

    @SerialName("status")
    val status: String = "",

    @SerialName("tagline")
    val tagline: String? = null
)