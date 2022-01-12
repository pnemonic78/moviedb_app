package com.tikal.tmdb.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.tikal.tmdb.R
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import com.tikal.tmdb.model.MoviesNowPlayingResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * TMDB local data source.
 */
class TmdbLocalDataSource @Inject constructor(@ApplicationContext val context: Context) :
    TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<Movie>> {
        val raw = context.resources.openRawResource(R.raw.movie_now_playing)
        val results = InputStreamReader(raw).use { reader ->
            Gson().fromJson(reader, MoviesNowPlayingResponse::class.java).results
        }
        return flowOf(results)
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetails> {
        val raw = context.resources.openRawResource(R.raw.movie_550)
        val results = InputStreamReader(raw).use { reader ->
            Gson().fromJson(reader, MovieDetails::class.java)
        }
        return flowOf(results)
    }
}