package com.tikal.tmdb.data.source.remote

import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
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

    private val cacheMovies = mutableMapOf<Long, MovieEntity>()
    private val cacheMovieDetails = mutableMapOf<Long, MovieEntity>()

    override suspend fun getMoviesNowPlaying(): Flow<List<MovieEntity>> {
        val cached = cacheMovies.values.sortedBy { it.id }
        if (cached.isNotEmpty()) return flowOf(cached)

        val result = service.getMoviesNowPlaying().results.map { it.toEntity() }
        cacheMovies(result)
        return flowOf(result)
    }

    override suspend fun getMovie(movieId: Long): Flow<MovieEntity> {
        val movieDetailsCached = cacheMovieDetails[movieId]
        if (movieDetailsCached != null) return flowOf(movieDetailsCached)

        return flow {
            val movieCached = cacheMovies[movieId]
            if (movieCached != null) emit(movieCached)

            val result = service.getMovieDetails(movieId).toEntity()
            cacheMovieDetails(result)
            emit(result)
        }
    }

    private fun cacheMovies(movies: List<MovieEntity>) {
        movies.forEach { movie -> cacheMovies[movie.id] = movie }
    }

    private fun cacheMovieDetails(movie: MovieEntity) {
        cacheMovieDetails[movie.id] = movie
    }
}