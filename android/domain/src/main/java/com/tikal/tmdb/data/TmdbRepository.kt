package com.tikal.tmdb.data

import com.tikal.flow.concat
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.data.source.local.TmdbLocalDataSource
import com.tikal.tmdb.data.source.remote.TmdbRemoteDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * TMDB data repository.
 */
class TmdbRepository @Inject constructor(
    private val localRepository: TmdbLocalDataSource,
    private val remoteRepository: TmdbRemoteDataSource
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<Movie>> {
        return concat(
            localRepository.getMoviesNowPlaying(),
            remoteRepository.getMoviesNowPlaying()
        )
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails> {
        return concat(
            localRepository.getMovieDetails(movieId),
            remoteRepository.getMovieDetails(movieId)
        )
    }
}