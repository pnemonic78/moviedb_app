package com.tmdbcodlab.android.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.model.Movie
import com.tmdbcodlab.android.model.MovieDetails
import com.tmdbcodlab.android.model.MoviesNowPlayingResponse
import io.reactivex.Observable
import java.io.InputStreamReader

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbLocalDataSource(val context: Context) : TmdbDataSource {
    override fun getMoviesNowPlaying(): Observable<List<Movie>> {
        val raw = context.resources.openRawResource(R.raw.movie_now_playing)
        val reader = InputStreamReader(raw)
        val response: MoviesNowPlayingResponse = Gson().fromJson(reader, MoviesNowPlayingResponse::class.java)
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