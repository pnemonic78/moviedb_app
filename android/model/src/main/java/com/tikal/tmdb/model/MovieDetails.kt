package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Calendar

/**
 * Movie details POJO.
 */
@Serializable
class MovieDetails : Movie {
    @SerialName("budget")
    val budget: Int

    @SerialName("homepage")
    val homepage: String?

    @SerialName("imdb_id")
    val imdbId: String?

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>?

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>?

    @SerialName("revenue")
    val revenue: Int?

    @SerialName("runtime")
    val runtime: Int?

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>

    @SerialName("status")
    val status: String

    @SerialName("tagline")
    val tagline: String?

    constructor(
        id: Long,
        adult: Boolean,
        backdropPath: String?,
        budget: Int = 0,
        genres: List<Genre> = emptyList(),
        homepage: String? = null,
        imdbId: String? = null,
        originalLanguage: String,
        originalTitle: String,
        overview: String?,
        popularity: Float,
        posterPath: String?,
        productionCompanies: List<ProductionCompany>? = null,
        productionCountries: List<ProductionCountry>? = null,
        releaseDate: Calendar?,
        revenue: Int? = null,
        runtime: Int? = null,
        spokenLanguages: List<SpokenLanguage> = emptyList(),
        status: String = "",
        tagline: String? = null,
        title: String,
        video: Boolean,
        voteAverage: Float,
        voteCount: Int
    ) : super(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genres.map { it.id }.toLongArray(),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    ) {
        this.budget = budget
        this.homepage = homepage
        this.imdbId = imdbId
        this.productionCompanies = productionCompanies
        this.productionCountries = productionCountries
        this.revenue = revenue
        this.runtime = runtime
        this.spokenLanguages = spokenLanguages
        this.status = status
        this.tagline = tagline
    }

    companion object {
        fun of(movie: Movie): MovieDetails {
            return MovieDetails(
                id = movie.id,
                adult = movie.adult,
                backdropPath = movie.backdropPath,
                genres = movie.genreIds.map { genreId -> Genre(genreId, genreId.toString()) },
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title,
                video = movie.video,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount
            )
        }
    }
}