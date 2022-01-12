package com.tikal.tmdb.model

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * Movie details POJO.
 */
data class MovieDetails(
    @SerializedName("id") var id: Long,
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") var backdropPath: String?,
    @SerializedName("budget") var budget: Int,
    @SerializedName("genres") var genres: List<Genre>,
    @SerializedName("homepage") var homepage: String?,
    @SerializedName("imdb_id") var imdbId: String?,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("overview") var overview: String?,
    @SerializedName("popularity") var popularity: Float,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("production_companies") var productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries") var productionCountries: List<ProductionCountry>,
    @SerializedName("release_date") var releaseDate: Date,
    @SerializedName("revenue") var revenue: Int?,
    @SerializedName("runtime") var runtime: Int?,
    @SerializedName("spoken_languages") var spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status") var status: String,
    @SerializedName("tagline") var tagline: String?,
    @SerializedName("title") var title: String,
    @SerializedName("video") var video: Boolean,
    @SerializedName("vote_average") var voteAverage: Float,
    @SerializedName("vote_count") var voteCount: Int
)

data class Genre(@SerializedName("id") var id: Long, @SerializedName("name") var name: String)

data class ProductionCompany(
    @SerializedName("id") var id: Long,
    @SerializedName("name") var name: String
)

data class ProductionCountry(
    @SerializedName("iso_3166_1") var id: String,
    @SerializedName("name") var name: String
)

data class SpokenLanguage(
    @SerializedName("iso_639_1") var id: String,
    @SerializedName("name") var name: String
)