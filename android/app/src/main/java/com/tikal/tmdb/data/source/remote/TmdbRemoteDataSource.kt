package com.tikal.tmdb.data.source.remote

import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * TMDB remote data source.
 */
class TmdbRemoteDataSource @Inject constructor(
    private val service: TmdbService
) : TmdbDataSource {

    private val cacheMovies = mutableMapOf<Long, Movie>()
    private val cacheMovieDetails = mutableMapOf<Long, MovieDetails>()

    override suspend fun getMoviesNowPlaying(): Flow<List<Movie>> {
        val result = service.getMoviesNowPlaying().results
        cacheMovies(result)
        return flowOf(result)
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails> {
        val movieDetailsCached = cacheMovieDetails[movieId]
        if (movieDetailsCached != null) return flowOf(movieDetailsCached)

        return flow {
            val movieCached = cacheMovies[movieId]
            if (movieCached != null) emit(MovieDetails.of(movieCached))

            val result = service.getMovieDetails(movieId)
            cacheMovieDetails(result)
            emit(result)
        }
    }

    private fun cacheMovies(movies: List<Movie>) {
        movies.forEach { movie -> cacheMovies[movie.id] = movie }
    }

    private fun cacheMovieDetails(movie: MovieDetails) {
        cacheMovieDetails[movie.id] = movie
    }
}