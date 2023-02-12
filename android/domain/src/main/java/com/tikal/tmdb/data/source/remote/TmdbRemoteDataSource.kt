package com.tikal.tmdb.data.source.remote

import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.domain.TmdbDb
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

/**
 * TMDB remote data source.
 */
class TmdbRemoteDataSource @Inject constructor(
    private val service: TmdbService,
    private val db: TmdbDb,
    private val ioDispatcher: CoroutineDispatcher
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<MovieEntity>> =
        withContext(ioDispatcher) {
            val movies = service.getMoviesNowPlaying().results.map { it.toEntity() }
            saveMovies(movies)
            flowOf(movies)
        }

    override suspend fun getMovie(movieId: Long): Flow<MovieEntity> =
        withContext(ioDispatcher) {
            val movie = service.getMovieDetails(movieId).toEntity()
            saveMovie(movie)
            flowOf(movie)
        }

    private fun saveMovies(movies: List<MovieEntity>) {
        val dao = db.movieDao()
        dao.deleteAll()
        dao.insert(movies)
    }

    private fun saveMovie(movie: MovieEntity) {
        val dao = db.movieDao()
        dao.insert(movie)
    }
}