package com.tikal.tmdb.data

import androidx.collection.LruCache
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.data.source.local.TmdbLocalDataSource
import com.tikal.tmdb.data.source.remote.TmdbRemoteDataSource
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbRepository @Inject constructor(
    private val localRepository: TmdbLocalDataSource,
    private val remoteRepository: TmdbRemoteDataSource
) : TmdbDataSource {

    private val cache = LruCache<String, Observable<*>>(100)

    override fun getMoviesNowPlaying(): Observable<List<Movie>> {
        val result = Observable.concat(
            localRepository.getMoviesNowPlaying(),
            remoteRepository.getMoviesNowPlaying()
        )

        return cacheObservable(CACHE_PREFIX_MOVIE_NOW_PLAYING, result)
    }

    override fun getMovieDetails(movieId: Long): Observable<MovieDetails> {
        val result = Observable.concat(
            localRepository.getMovieDetails(movieId),
            remoteRepository.getMovieDetails(movieId)
        )

        return cacheObservable(CACHE_PREFIX_MOVIE_DETAILS + movieId, result)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> cacheObservable(key: String, observable: Observable<T>): Observable<T> {
        val cachedObservable: Observable<T>? = cache.get(key) as Observable<T>?
        if (cachedObservable != null) {
            return cachedObservable
        }
        cache.put(key, observable)
        return observable
    }

    companion object {
        private const val CACHE_PREFIX_MOVIE_NOW_PLAYING = "getMoviesNowPlaying/"
        private const val CACHE_PREFIX_MOVIE_DETAILS = "getMovieDetails/"
    }
}