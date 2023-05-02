package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.DateTimeSerializer
import com.tikal.tmdb.json.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Movie POJO.
 */
@Serializable
data class Movie(
    @SerialName("adult")
    override val adult: Boolean = false,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("genre_ids")
    val genreIds: LongArray = longArrayOf(),

    @SerialName("id")
    override val id: Long,

    @SerialName("media_type")
    override val mediaType: MediaType = MediaType.movie,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("overview")
    val overview: String?,

    @SerialName("popularity")
    override val popularity: Double = 0.0,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @Serializable(with = DateTimeSerializer::class)
    @SerialName("release_date")
    val releaseDate: DateTime? = null,

    @SerialName("title")
    val title: String,

    @SerialName("video")
    val video: Boolean = false,

    @SerialName("vote_average")
    val voteAverage: Double = 0.0,

    @SerialName("vote_count")
    val voteCount: Int = 0,

    @SerialName("budget")
    val budget: Long = 0,

//    @SerialName("credits")
//    val credits: CreditsResponse? = null,

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
    val revenue: Long? = null,

    @SerialName("runtime")
    val runtime: Int? = null,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,

    @SerialName("status")
    val status: String = "",

    @SerialName("tagline")
    val tagline: String? = null
) : Media()