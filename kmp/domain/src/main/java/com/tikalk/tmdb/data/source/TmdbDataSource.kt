package com.tikalk.tmdb.data.source

import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPage

/**
 * TMDB data source.
 */
interface TmdbDataSource {

    suspend fun getMoviesNowPlaying(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMoviesPopular(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMoviesTopRated(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMoviesUpcoming(page: Int = 1, refresh: Boolean = false): MoviesPage?

    suspend fun getMovie(movieId: Long): MovieEntity?
}