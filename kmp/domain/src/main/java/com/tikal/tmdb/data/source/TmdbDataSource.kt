package com.tikal.tmdb.data.source

import com.tikal.tmdb.json.model.Movie
import com.tikal.tmdb.json.model.MoviesPage

/**
 * TMDB data source.
 */
interface TmdbDataSource {

    suspend fun getMoviesNowPlaying(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMoviesPopular(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMoviesTopRated(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMoviesUpcoming(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMovie(movieId: Long): Movie?
}