package com.tikal.tmdb.data.source.local

import com.tikal.tmdb.data.model.GenreEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.model.MoviesPageType
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.domain.TmdbDb
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

/**
 * TMDB local data source.
 */
class TmdbLocalDataSource @Inject constructor(
    private val db: TmdbDb,
    private val ioDispatcher: CoroutineDispatcher
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<MoviesPage>> =
        withContext(ioDispatcher) {
            val dao = db.moviePagesDao()
            dao.getByType(MoviesPageType.NOW_PLAYING)
        }

    override suspend fun getMovie(movieId: Long): Flow<MovieEntity> =
        withContext(ioDispatcher) {
            val dao = db.movieDao()
            val movie = dao.getById(movieId)
            populateMovie(movie)
            flowOf(movie)
        }

    private suspend fun populateMovie(movie: MovieEntity) {
        movie.genres = getGenres(movie.genreIds)
    }

    private suspend fun getGenres(genreIds: LongArray): List<GenreEntity>? {
        if (genreIds.isEmpty()) return null

        val dao = db.genreDao()
        return dao.getByIds(genreIds)
    }
}