package com.tikal.tmdb.data.source

import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.Flow

/**
 * TMDB data source.
 */
interface TmdbDataSource {

    suspend fun getMoviesNowPlaying(): Flow<List<Movie>>

    suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails>
}