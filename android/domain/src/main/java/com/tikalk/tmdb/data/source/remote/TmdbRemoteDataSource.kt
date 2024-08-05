package com.tikalk.tmdb.data.source.remote

import com.tikalk.tmdb.api.TmdbService
import com.tikalk.tmdb.data.model.GenreEntity
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPage
import com.tikalk.tmdb.data.model.MoviesPageCrossRef
import com.tikalk.tmdb.data.model.MoviesPageEntity
import com.tikalk.tmdb.data.model.MoviesPageType
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.domain.TmdbDb
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * TMDB remote data source.
 */
class TmdbRemoteDataSource @Inject constructor(
    private val service: TmdbService,
    private val db: TmdbDb,
    private val ioDispatcher: CoroutineDispatcher
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            val entity = service.getMoviesNowPlaying(page = page)
                .toEntity(MoviesPageType.NOW_PLAYING)
            savePage(entity)
            entity
        }

    override suspend fun getMoviesPopular(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            val entity = service.getMoviesPopular(page = page)
                .toEntity(MoviesPageType.POPULAR)
            savePage(entity)
            entity
        }

    override suspend fun getMoviesTopRated(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            val entity = service.getMoviesTopRated(page = page)
                .toEntity(MoviesPageType.TOP_RATED)
            savePage(entity)
            entity
        }

    override suspend fun getMoviesUpcoming(page: Int, refresh: Boolean): MoviesPage =
        withContext(ioDispatcher) {
            val entity = service.getMoviesUpcoming(page = page)
                .toEntity(MoviesPageType.UPCOMING)
            savePage(entity)
            entity
        }

    override suspend fun getMovie(movieId: Long): MovieEntity = withContext(ioDispatcher) {
        val movie = service.getMovieDetails(movieId, append = "credits").toEntity()
        saveMovie(movie)
        movie
    }

    private suspend fun savePage(page: MoviesPage) {
        savePage(page.page)
        saveMovies(page.movies)
        savePageMovieCrossRef(page)
    }

    private suspend fun savePage(entity: MoviesPageEntity) {
        val dao = db.moviePagesDao()
        if (entity.page <= 1) {
            dao.deleteByType(entity.type)
        }
        dao.insert(entity)
    }

    private suspend fun saveMovies(movies: List<MovieEntity>) {
        val dao = db.movieDao()
        dao.insert(movies)

        saveMoviesGenres(movies)
    }

    private suspend fun saveMovie(movie: MovieEntity) {
        val dao = db.movieDao()
        dao.insert(movie)

        saveMovieGenres(movie)
    }

    private suspend fun saveMoviesGenres(movies: List<MovieEntity>) {
        movies.forEach { saveMovieGenres(it) }
    }

    private suspend fun saveMovieGenres(movie: MovieEntity) {
        movie.genres?.let { saveGenres(it) }
    }

    private suspend fun saveGenres(genres: List<GenreEntity>) {
        val dao = db.genreDao()
        dao.insert(genres)
    }

    private suspend fun savePageMovieCrossRef(entity: MoviesPage) {
        val pageId = entity.page.id
        val keys = entity.movies.map { MoviesPageCrossRef(pageId = pageId, movieId = it.id) }

        val dao = db.moviesPageCrossRefDao()
        dao.deleteByPage(pageId)
        dao.insert(keys)
    }
}