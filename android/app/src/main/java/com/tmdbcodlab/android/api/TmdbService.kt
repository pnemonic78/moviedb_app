package com.tmdbcodlab.android.api

import com.tmdbcodlab.android.BuildConfig
import com.tmdbcodlab.android.model.MovieDetails
import com.tmdbcodlab.android.model.MoviesNowPlayingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale

/**
 * Created by ronelg on 12/19/17.
 */
interface TmdbService {

    @GET("movie/now_playing")
    fun getMoviesNowPlaying(@Query("api_key") apiKey: String? = BuildConfig.MDB_API_KEY,
                            @Query("language") language: String? = Locale.getDefault().language,
                            @Query("page") page: Int? = 1,
                            @Query("region") region: String? = null): Observable<MoviesNowPlayingResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") moveId: Long,
                        @Query("api_key") apiKey: String? = BuildConfig.MDB_API_KEY,
                        @Query("language") language: String? = Locale.getDefault().language,
                        @Query("append_to_response") append: String? = null): Observable<MovieDetails>

}