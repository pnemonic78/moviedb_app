package com.tikal.tmdb.model

import com.tikal.tmdb.json.DateTime
import com.tikal.tmdb.json.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Movie POJO.
 */
@Serializable
class Movie : Media() {
    init {
        mediaType = MediaType.Movie
    }

    @SerialName("backdrop_path")
    var backdropPath: String? = null

    @SerialName("genre_ids")
    var genreIds: LongArray = longArrayOf()

    @SerialName("original_language")
    var originalLanguage: String = ""

    @SerialName("original_title")
    var originalTitle: String = ""

    @SerialName("overview")
    var overview: String? = null

    @SerialName("poster_path")
    var posterPath: String? = null

    @Serializable(with = DateTimeSerializer::class)
    @SerialName("release_date")
    var releaseDate: DateTime? = null

    @SerialName("title")
    var title: String = ""

    @SerialName("video")
    var video: Boolean = false

    @SerialName("vote_average")
    var voteAverage: Double = 0.0

    @SerialName("vote_count")
    var voteCount: Int = 0

    @SerialName("budget")
    var budget: Long = 0

    @SerialName("credits")
    var credits: Credits? = null

    @SerialName("genres")
    var genres: List<Genre> = emptyList()

    @SerialName("homepage")
    var homepage: String? = null

    @SerialName("imdb_id")
    var imdbId: String? = null

    @SerialName("production_companies")
    var productionCompanies: List<ProductionCompany> = emptyList()

    @SerialName("production_countries")
    var productionCountries: List<ProductionCountry> = emptyList()

    @SerialName("revenue")
    var revenue: Long? = null

    @SerialName("runtime")
    var runtime: Int? = null

    @SerialName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage> = emptyList()

    @SerialName("status")
    var status: String = ""

    @SerialName("tagline")
    var tagline: String? = null

    override fun toString(): String {
        return title
    }
}