package com.tikal.tmdb.data

import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.data.source.local.TmdbLocalDataSource
import com.tikal.tmdb.data.source.remote.TmdbRemoteDataSource
import com.tikal.tmdb.data.model.MoviesPage

/**
 * TMDB data repository.
 */
class TmdbRepository(
    private val localRepository: TmdbLocalDataSource,
    private val remoteRepository: TmdbRemoteDataSource
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(page: Int, refresh: Boolean): MoviesPage {
        var result = localRepository.getMoviesNowPlaying(page = page)

        // Fetch from server and save to database.
        if ((result == null) || refresh) {
            result = remoteRepository.getMoviesNowPlaying(page = page)
        }

        return result
    }

    override suspend fun getMoviesPopular(page: Int, refresh: Boolean): MoviesPage {
        var result = localRepository.getMoviesPopular(page = page)

        // Fetch from server and save to database.
        if ((result == null) || refresh) {
            result = remoteRepository.getMoviesPopular(page = page)
        }
        return result
    }

    override suspend fun getMoviesTopRated(page: Int, refresh: Boolean): MoviesPage {
        var result = localRepository.getMoviesTopRated(page = page)

        // Fetch from server and save to database.
        if ((result == null) || refresh) {
            result = remoteRepository.getMoviesTopRated(page = page)
        }
        return result
    }

    override suspend fun getMoviesUpcoming(page: Int, refresh: Boolean): MoviesPage {
        var result = localRepository.getMoviesUpcoming(page = page)

        // Fetch from server and save to database.
        if ((result == null) || refresh) {
            result = remoteRepository.getMoviesUpcoming(page = page)
        }
        return result
    }

    override suspend fun getMovie(movieId: Long): MovieEntity {
        var result = localRepository.getMovie(movieId)

        // Fetch from server and save to database.
        if (result == null) {
            result = remoteRepository.getMovie(movieId)
        }
        return result
    }
}