package com.tikalk.tmdb.data.source.remote

import com.tikalk.tmdb.api.TmdbService
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.data.model.MoviesPage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * TMDB remote data source.
 */
class TmdbRemoteDataSource(
    private val service: TmdbService,
    private val ioDispatcher: CoroutineDispatcher
) : TmdbDataSource {
    override suspend fun getMoviesNowPlaying(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            service.getMoviesNowPlaying(page = page)
        }

    override suspend fun getMoviesPopular(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            service.getMoviesPopular(page = page)
        }

    override suspend fun getMoviesTopRated(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            service.getMoviesTopRated(page = page)
        }

    override suspend fun getMoviesUpcoming(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            service.getMoviesUpcoming(page = page)
        }

    override suspend fun getMovie(movieId: Long): MovieEntity =
        withContext(ioDispatcher) {
            service.getMovieDetails(movieId=movieId)
        }
}