package com.tikal.tmdb.data.source.remote

import com.tikal.tmdb.data.model.DatesEntity
import com.tikal.tmdb.data.model.GenreEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.model.MoviesPageEntity
import com.tikal.tmdb.data.model.MoviesPageType
import com.tikal.tmdb.data.model.ProductionCompanyEntity
import com.tikal.tmdb.data.model.ProductionCountryEntity
import com.tikal.tmdb.data.model.SpokenLanguageEntity
import com.tikal.tmdb.data.model.TrailerEntity
import com.tikal.tmdb.json.model.Dates
import com.tikal.tmdb.json.model.Genre
import com.tikal.tmdb.json.model.Movie
import com.tikal.tmdb.json.model.MoviesPageResponse
import com.tikal.tmdb.json.model.ProductionCompany
import com.tikal.tmdb.json.model.ProductionCountry
import com.tikal.tmdb.json.model.SpokenLanguage
import com.tikal.tmdb.json.model.Trailer

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

    return MovieEntity(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genreIds = genreIds,
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    ).apply {
        genres = movie.genres?.map { it.toEntity() }
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
        totalResult = totalResult,
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

fun Trailer.toEntity() = TrailerEntity(
    id = id
)
