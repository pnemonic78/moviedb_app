package com.tikal.tmdb.data.source.local

import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.domain.TmdbDb
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * TMDB local data source.
 */
class TmdbLocalDataSource @Inject constructor(
    private val db: TmdbDb,
    private val ioDispatcher: CoroutineDispatcher
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<MovieEntity>> =
        withContext(ioDispatcher) {
            val dao = db.movieDao()
            dao.all
        }

    override suspend fun getMovie(movieId: Long): Flow<MovieEntity> =
        withContext(ioDispatcher) {
            val dao = db.movieDao()
            dao.getById(movieId)
        }
}