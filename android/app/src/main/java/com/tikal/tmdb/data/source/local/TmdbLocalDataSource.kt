package com.tikal.tmdb.data.source.local

import android.content.Context
import com.tikal.tmdb.R
import com.tikal.tmdb.data.source.TmdbDataSource
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

    override suspend fun getMoviesNowPlaying(): Flow<List<Movie>> {
        val raw = context.resources.openRawResource(R.raw.movie_now_playing)
        val results = raw.use { stream ->
            json.decodeFromStream<MoviesNowPlayingResponse>(stream).results
        }
        return flowOf(results)
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails> {
        val raw = context.resources.openRawResource(R.raw.movie_550)
        val results = raw.use { stream ->
            json.decodeFromStream<MovieDetails>(stream)
        }
        return flowOf(results)
    }
}