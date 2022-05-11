package com.tikal.tmdb.data.source.local

import android.content.Context
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.domain.R
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import com.tikal.tmdb.model.MoviesNowPlayingResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

/**
 * TMDB local data source.
 */
class TmdbLocalDataSource @Inject constructor(
    @ApplicationContext val context: Context,
    private val json: Json
) : TmdbDataSource {

    private val cacheMovies = mutableMapOf<Long, Movie>()
    private val cacheMovieDetails = mutableMapOf<Long, MovieDetails>()

    override suspend fun getMoviesNowPlaying(): Flow<List<Movie>> {
        val raw = context.resources.openRawResource(R.raw.movie_now_playing)
        val result = raw.use { stream ->
            json.decodeFromStream<MoviesNowPlayingResponse>(stream).results
        }
        cacheMovies(result)
        return flowOf(result)
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails> {
        val movieDetailsCached = cacheMovieDetails[movieId]
        if (movieDetailsCached != null) return flowOf(movieDetailsCached)

        val movieCached = cacheMovies[movieId]
        if (movieCached != null) return flowOf(MovieDetails.of(movieCached))

        val raw = context.resources.openRawResource(R.raw.movie_550)
        val result = raw.use { stream ->
            json.decodeFromStream<MovieDetails>(stream)
        }
        cacheMovieDetails(result)
        return flowOf(result)
    }

    private fun cacheMovies(movies: List<Movie>) {
        movies.forEach { movie -> cacheMovies[movie.id] = movie }
    }

    private fun cacheMovieDetails(movie: MovieDetails) {
        cacheMovieDetails[movie.id] = movie
    }
}