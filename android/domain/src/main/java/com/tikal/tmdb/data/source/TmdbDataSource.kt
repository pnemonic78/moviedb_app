package com.tikal.tmdb.data.source

import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import kotlinx.coroutines.flow.Flow

/**
 * TMDB data source.
 */
interface TmdbDataSource {

    suspend fun getMoviesNowPlaying(): Flow<List<MoviesPage>>

    suspend fun getMoviesPopular(): Flow<List<MoviesPage>>

    suspend fun getMovie(movieId: Long): Flow<MovieEntity>
}