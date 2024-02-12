package com.tikal.tmdb.api

import com.tikal.tmdb.domain.BuildConfig
import com.tikal.tmdb.json.model.CreditsResponse
import com.tikal.tmdb.json.model.ImagesResponse
import com.tikal.tmdb.json.model.Movie
import com.tikal.tmdb.json.model.MoviesPageResponse
import com.tikal.tmdb.json.model.Person
import com.tikal.tmdb.json.model.VideosResponse
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
    ): MoviesPageResponse

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): MoviesPageResponse

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): MoviesPageResponse

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): MoviesPageResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("append_to_response") append: String? = null
    ): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): CreditsResponse

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("include_image_language") languageInclude: String = "en",
    ): ImagesResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
    ): VideosResponse

    @GET("person/{person_id}")
    suspend fun getPerson(
        @Path("person_id") personId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("append_to_response") append: String? = null
    ): Person

}