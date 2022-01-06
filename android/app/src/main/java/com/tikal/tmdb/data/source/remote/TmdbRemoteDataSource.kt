package com.tikal.tmdb.data.source.remote

import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbRemoteDataSource @Inject constructor(private val service: TmdbService) : TmdbDataSource {

    override fun getMoviesNowPlaying(): Observable<List<Movie>> {
        return service.getMoviesNowPlaying()
            .map { it.results }
            .cache()
    }

    override fun getMovieDetails(movieId: Long): Observable<MovieDetails> {
        return service.getMovieDetails(movieId).cache()
    }
}