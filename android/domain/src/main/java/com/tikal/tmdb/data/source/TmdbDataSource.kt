package com.tikal.tmdb.data.source

import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * TMDB data source.
 */
interface TmdbDataSource {

    suspend fun getMoviesNowPlaying(): Flow<List<MovieEntity>>

    suspend fun getMovie(movieId: Long): Flow<MovieEntity>
}