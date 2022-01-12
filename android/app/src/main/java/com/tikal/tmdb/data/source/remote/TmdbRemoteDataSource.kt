package com.tikal.tmdb.data.source.remote

import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * TMDB remote data source.
 */
class TmdbRemoteDataSource @Inject constructor(private val service: TmdbService) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<Movie>> {
        return flowOf(service.getMoviesNowPlaying().results)
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails> {
        return flowOf(service.getMovieDetails(movieId))
    }
}