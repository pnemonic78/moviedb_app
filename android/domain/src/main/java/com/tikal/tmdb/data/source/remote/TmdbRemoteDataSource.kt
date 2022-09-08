package com.tikal.tmdb.data.source.remote

import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.domain.TmdbDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * TMDB remote data source.
 */
class TmdbRemoteDataSource @Inject constructor(
    private val service: TmdbService,
    private val db: TmdbDb
) : TmdbDataSource {

    private val cacheMovies = mutableMapOf<Long, MovieEntity>()
    private val cacheMovieDetails = mutableMapOf<Long, MovieEntity>()

    override suspend fun getMoviesNowPlaying(): Flow<List<MovieEntity>> =
        withContext(Dispatchers.IO) {
            val cached = cacheMovies.values.sortedBy { it.id }
            if (cached.isNotEmpty()) return@withContext flowOf(cached)

            val movies = service.getMoviesNowPlaying().results.map { it.toEntity() }
            saveMovies(movies)

            flowOf(movies)
        }

    override suspend fun getMovie(movieId: Long): Flow<MovieEntity> = withContext(Dispatchers.IO) {
        val movieDetailsCached = cacheMovieDetails[movieId]
        if (movieDetailsCached != null) return@withContext flowOf(movieDetailsCached)

        flow {
            val movieCached = cacheMovies[movieId]
            if (movieCached != null) emit(movieCached)

            val movies = service.getMovieDetails(movieId).toEntity()
            cacheMovieDetails(movies)
            emit(movies)
        }
    }

    private fun cacheMovies(movies: List<MovieEntity>) {
        movies.forEach { movie -> cacheMovies[movie.id] = movie }
    }

    private fun cacheMovieDetails(movie: MovieEntity) {
        cacheMovieDetails[movie.id] = movie
    }

    private fun saveMovies(movies: List<MovieEntity>) {
        cacheMovies(movies)

        val dao = db.movieDao()
        dao.deleteAll()
        dao.insert(movies)
    }
}