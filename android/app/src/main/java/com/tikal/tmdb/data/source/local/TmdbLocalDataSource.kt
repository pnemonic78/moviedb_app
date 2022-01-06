package com.tikal.tmdb.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.tikal.tmdb.R
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import com.tikal.tmdb.model.MoviesNowPlayingResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbLocalDataSource @Inject constructor(@ApplicationContext val context: Context) :
    TmdbDataSource {

    override fun getMoviesNowPlaying(): Observable<List<Movie>> {
        val raw = context.resources.openRawResource(R.raw.movie_now_playing)
        val reader = InputStreamReader(raw)
        val response: MoviesNowPlayingResponse = Gson()
            .fromJson(reader, MoviesNowPlayingResponse::class.java)
        reader.close()
        return Observable.fromArray(response.results)
    }

    override fun getMovieDetails(movieId: Long): Observable<MovieDetails> {
        val raw = context.resources.openRawResource(R.raw.movie_550)
        val reader = InputStreamReader(raw)
        val response: MovieDetails = Gson().fromJson(reader, MovieDetails::class.java)
        reader.close()
        return Observable.just(response)
    }
}