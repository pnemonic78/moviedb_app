package com.tikal.tmdb.api

import com.tikal.tmdb.model.Credits
import com.tikal.tmdb.model.Images
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MoviesPage
import com.tikal.tmdb.model.Person
import com.tikal.tmdb.model.Videos
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.util.Locale

/**
 * TMDB API Web Service.
 */
class TmdbService(private val baseUrl: String, private val client: HttpClient) {

    suspend fun getMoviesNowPlaying(
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
        page: Int = 1,
        region: String? = null
    ): MoviesPage = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/now_playing")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
            parameters.append("page", page.toString())
            region?.let { parameters.append("region", region) }
        }
    }.body<MoviesPage>()

    suspend fun getMoviesPopular(
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
        page: Int = 1,
        region: String? = null
    ): MoviesPage = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/popular")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
            parameters.append("page", page.toString())
            region?.let { parameters.append("region", region) }
        }
    }.body<MoviesPage>()

    suspend fun getMoviesTopRated(
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
        page: Int = 1,
        region: String? = null
    ): MoviesPage = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/top_rated")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
            parameters.append("page", page.toString())
            region?.let { parameters.append("region", region) }
        }
    }.body<MoviesPage>()

    suspend fun getMoviesUpcoming(
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
        page: Int = 1,
        region: String? = null
    ): MoviesPage = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/upcoming")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
            parameters.append("page", page.toString())
            region?.let { parameters.append("region", region) }
        }
    }.body<MoviesPage>()

    suspend fun getMovieDetails(
        movieId: Long,
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
        append: String? = null
    ): Movie = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/${movieId}")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
            append?.let { parameters.append("append_to_response", append) }
        }
    }.body<Movie>()

    suspend fun getMovieCredits(
        movieId: Long,
        apiKey: String = TMDB_API_KEY,
    ): Credits = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/${movieId}/credits")
            parameters.append("api_key", apiKey)
        }
    }.body<Credits>()

    suspend fun getMovieImages(
        movieId: Long,
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
        languageInclude: String = "en",
    ): Images = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/${movieId}/images")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
            parameters.append("include_image_language", languageInclude)
        }
    }.body<Images>()

    suspend fun getMovieVideos(
        movieId: Long,
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
    ): Videos = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("movie/${movieId}/videos")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
        }
    }.body<Videos>()

    suspend fun getPerson(
        personId: Long,
        apiKey: String = TMDB_API_KEY,
        language: String = Locale.getDefault().language,
        append: String? = null
    ): Person = client.get(baseUrl) {
        method = HttpMethod.Get
        url {
            appendPathSegments("person/${personId}")
            parameters.append("api_key", apiKey)
            parameters.append("language", language)
            append?.let { parameters.append("append_to_response", append) }
        }
    }.body<Person>()

    companion object {
        private const val TMDB_API_KEY = ""
    }
}