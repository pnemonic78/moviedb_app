package com.tikal.tmdb.data

import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.data.source.local.TmdbLocalDataSource
import com.tikal.tmdb.data.source.remote.TmdbRemoteDataSource
import javax.inject.Inject

/**
 * TMDB data repository.
 */
class TmdbRepository @Inject constructor(
    private val localRepository: TmdbLocalDataSource,
    private val remoteRepository: TmdbRemoteDataSource
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(page: Int): MoviesPage {
        // Fetch from server and save to database.
        remoteRepository.getMoviesNowPlaying(page = page)

        return localRepository.getMoviesNowPlaying(page = page)
    }

    override suspend fun getMoviesPopular(page: Int): MoviesPage {
        // Fetch from server and save to database.
        remoteRepository.getMoviesPopular(page = page)

        return localRepository.getMoviesPopular(page = page)
    }

    override suspend fun getMovie(movieId: Long): MovieEntity {
        // Fetch from server and save to database.
        remoteRepository.getMovie(movieId)

        return localRepository.getMovie(movieId)
    }
}