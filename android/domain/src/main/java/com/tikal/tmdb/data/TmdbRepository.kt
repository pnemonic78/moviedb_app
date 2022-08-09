package com.tikal.tmdb.data

import com.tikal.flow.concat
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.data.source.local.TmdbLocalDataSource
import com.tikal.tmdb.data.source.remote.TmdbRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * TMDB data repository.
 */
class TmdbRepository @Inject constructor(
    private val localRepository: TmdbLocalDataSource,
    private val remoteRepository: TmdbRemoteDataSource
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<MovieEntity>> {
        return concat(
            localRepository.getMoviesNowPlaying(),
            remoteRepository.getMoviesNowPlaying()
        )
    }

    override suspend fun getMovie(movieId: Long): Flow<MovieEntity> {
        return concat(
            localRepository.getMovie(movieId),
            remoteRepository.getMovie(movieId)
        )
    }
}