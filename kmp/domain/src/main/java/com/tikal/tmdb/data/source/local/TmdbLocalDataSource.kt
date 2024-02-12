package com.tikal.tmdb.data.source.local

import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MoviesPage
import kotlinx.coroutines.CoroutineDispatcher

/**
 * TMDB local data source.
 */
class TmdbLocalDataSource(
    private val ioDispatcher: CoroutineDispatcher
) : TmdbDataSource {
    override suspend fun getMoviesNowPlaying(page: Int, refresh: Boolean): MoviesPage? = null

    override suspend fun getMoviesPopular(page: Int, refresh: Boolean): MoviesPage? = null

    override suspend fun getMoviesTopRated(page: Int, refresh: Boolean): MoviesPage? = null

    override suspend fun getMoviesUpcoming(page: Int, refresh: Boolean): MoviesPage? = null

    override suspend fun getMovie(movieId: Long): Movie? = null
}