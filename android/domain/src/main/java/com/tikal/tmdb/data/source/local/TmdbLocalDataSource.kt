package com.tikal.tmdb.data.source.local

import com.tikal.tmdb.data.model.GenreEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.domain.TmdbDb
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * TMDB local data source.
 */
class TmdbLocalDataSource @Inject constructor(
    private val db: TmdbDb,
    private val ioDispatcher: CoroutineDispatcher
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(page: Int, refresh: Boolean): MoviesPage? =
        withContext(ioDispatcher) {
            val dao = db.moviePagesDao()
            dao.getNowPlaying(page)
        }

    override suspend fun getMoviesPopular(page: Int, refresh: Boolean): MoviesPage? =
        withContext(ioDispatcher) {
            val dao = db.moviePagesDao()
            dao.getPopular(page)
        }

    override suspend fun getMoviesTopRated(page: Int, refresh: Boolean): MoviesPage? =
        withContext(ioDispatcher) {
            val dao = db.moviePagesDao()
            dao.getTopRated(page)
        }

    override suspend fun getMovie(movieId: Long): MovieEntity? = withContext(ioDispatcher) {
        val dao = db.movieDao()
        val movie = dao.getById(movieId)
        if (movie != null) populateMovie(movie)
        movie
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