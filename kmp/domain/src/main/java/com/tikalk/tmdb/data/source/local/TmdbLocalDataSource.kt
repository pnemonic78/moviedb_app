package com.tikalk.tmdb.data.source.local

import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.data.model.MoviesPage
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

    override suspend fun getMovie(movieId: Long): MovieEntity? = null
}