package com.tikal.tmdb.model

import com.tikal.tmdb.json.DateCalendarSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Calendar

/**
 * Movie details POJO.
 */
@Serializable
data class MovieDetails(
    @SerialName("id") var id: Long,
    @SerialName("adult") var adult: Boolean,
    @SerialName("backdrop_path") var backdropPath: String?,
    @SerialName("budget") var budget: Int,
    @SerialName("genres") var genres: List<Genre>,
    @SerialName("homepage") var homepage: String?,
    @SerialName("imdb_id") var imdbId: String?,
    @SerialName("original_language") var originalLanguage: String,
    @SerialName("original_title") var originalTitle: String,
    @SerialName("overview") var overview: String?,
    @SerialName("popularity") var popularity: Float,
    @SerialName("poster_path") var posterPath: String?,
    @SerialName("production_companies") var productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries") var productionCountries: List<ProductionCountry>,
    @SerialName("release_date")
    @Serializable(with = DateCalendarSerializer::class)
    var releaseDate: Calendar?,
    @SerialName("revenue") var revenue: Int?,
    @SerialName("runtime") var runtime: Int?,
    @SerialName("spoken_languages") var spokenLanguages: List<SpokenLanguage>,
    @SerialName("status") var status: String,
    @SerialName("tagline") var tagline: String?,
    @SerialName("title") var title: String,
    @SerialName("video") var video: Boolean,
    @SerialName("vote_average") var voteAverage: Float,
    @SerialName("vote_count") var voteCount: Int
)