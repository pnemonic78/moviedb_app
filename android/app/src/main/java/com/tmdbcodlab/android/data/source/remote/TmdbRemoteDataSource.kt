package com.tmdbcodlab.android.data.source.remote

import com.tmdbcodlab.android.api.TmdbService
import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.model.Movie
import com.tmdbcodlab.android.model.MovieDetails
import io.reactivex.Observable

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbRemoteDataSource(val service: TmdbService) : TmdbDataSource {

    override fun getMoviesNowPlaying(): Observable<List<Movie>> {
        return service.getMoviesNowPlaying()
                .map { it.results }
                .cache()
    }

    override fun getMovieDetails(movieId: Long): Observable<MovieDetails> {
        return service.getMovieDetails(movieId).cache()
    }
}