package com.tikal.tmdb.api

import com.tikal.tmdb.domain.BuildConfig
import com.tikal.tmdb.json.model.Movie
import com.tikal.tmdb.json.model.MoviesNowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale

/**
 * TMDB API Web Service.
 */
interface TmdbService {

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
            @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
            @Query("language") language: String = Locale.getDefault().language,
            @Query("page") page: Int = 1,
            @Query("region") region: String? = null
    ): MoviesNowPlayingResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
            @Path("movie_id") moveId: Long,
            @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
            @Query("language") language: String = Locale.getDefault().language,
            @Query("append_to_response") append: String? = null
    ): Movie

}