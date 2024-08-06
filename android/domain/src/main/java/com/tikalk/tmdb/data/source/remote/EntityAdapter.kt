package com.tikalk.tmdb.data.source.remote

import com.tikalk.tmdb.data.model.DatesEntity
import com.tikalk.tmdb.data.model.GenreEntity
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPage
import com.tikalk.tmdb.data.model.MoviesPageEntity
import com.tikalk.tmdb.data.model.MoviesPageType
import com.tikalk.tmdb.data.model.ProductionCompanyEntity
import com.tikalk.tmdb.data.model.ProductionCountryEntity
import com.tikalk.tmdb.data.model.SpokenLanguageEntity
import com.tikalk.tmdb.json.model.Dates
import com.tikalk.tmdb.json.model.Genre
import com.tikalk.tmdb.json.model.Movie
import com.tikalk.tmdb.json.model.MoviesPageResponse
import com.tikalk.tmdb.json.model.ProductionCompany
import com.tikalk.tmdb.json.model.ProductionCountry
import com.tikalk.tmdb.json.model.SpokenLanguage

fun Dates.toEntity() = DatesEntity(
    maximum = maximum,
    minimum = minimum
)

fun Genre.toEntity() = GenreEntity(
    id = id,
    name = name
)

fun Movie.toEntity(): MovieEntity {
    val movie = this

    return MovieEntity().apply {
        adult = movie.adult
        backdropPath = movie.backdropPath
        budget = movie.budget
        genreIds = movie.genreIds
        homepage = movie.homepage
        id = movie.id
        imdbId = movie.imdbId
        originalLanguage = movie.originalLanguage
        originalTitle = movie.originalTitle
        overview = movie.overview
        popularity = movie.popularity
        posterPath = movie.posterPath
        releaseDate = movie.releaseDate
        revenue = movie.revenue
        runtime = movie.runtime
        status = movie.status
        tagline = movie.tagline
        title = movie.title
        video = movie.video
        voteAverage = movie.voteAverage
        voteCount = movie.voteCount
        genres = movie.genres.map { it.toEntity() }
//    productionCompanies = productionCompanies?.map { it.toEntity() },
//    productionCountries = productionCountries?.map { it.toEntity() },
//    spokenLanguages = spokenLanguages?.map { it.toEntity() },
    }
}

fun MoviesPageResponse.toEntity(type: MoviesPageType) = MoviesPage(
    page = MoviesPageEntity(
        dates = dates?.toEntity(),
        page = page,
        totalPages = totalPages,
        totalResults = totalResults,
        type = type
    ),
    movies = results.map { it.toEntity() }
)

fun ProductionCompany.toEntity() = ProductionCompanyEntity(
    id = id,
    name = name
)

fun ProductionCountry.toEntity() = ProductionCountryEntity(
    id = id,
    name = name
)

fun SpokenLanguage.toEntity() = SpokenLanguageEntity(
    id = id,
    name = name
)
