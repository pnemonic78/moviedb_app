package com.tikal.tmdb

import com.tikal.tmdb.data.model.DatesEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.model.MoviesPageEntity
import com.tikal.tmdb.data.model.MoviesPageType
import java.util.Calendar

val movie550 = MovieEntity(
    adult = false,
    backdropPath = "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg",
    id = 550,
    originalLanguage = "en",
    originalTitle = "Fight Club",
    overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
    popularity = 46.747329f,
    posterPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
    releaseDate = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1999)
        set(Calendar.MONTH, Calendar.OCTOBER)
        set(Calendar.DAY_OF_MONTH, 15)
    },
    title = "Fight Club",
    video = false,
    voteAverage = 8.3f,
    voteCount = 11400
)

private val moviesList = listOf(movie550, movie550, movie550)

val page550 = MoviesPage(
    page = MoviesPageEntity(
        dates = DatesEntity(),
        page = 1,
        totalPages = 1,
        totalResult = moviesList.size,
        type = MoviesPageType.NOW_PLAYING
    ),
    movies = moviesList
)