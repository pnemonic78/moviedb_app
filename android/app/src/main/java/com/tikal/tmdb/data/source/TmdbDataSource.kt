package com.tikal.tmdb.data.source

import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import io.reactivex.Observable

/**
 * Created by ronelg on 12/19/17.
 */
interface TmdbDataSource {

    fun getMoviesNowPlaying(): Observable<List<Movie>>

    fun getMovieDetails(movieId: Long): Observable<MovieDetails>
}