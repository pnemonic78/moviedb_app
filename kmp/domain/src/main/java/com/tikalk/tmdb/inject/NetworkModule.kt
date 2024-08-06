package com.tikalk.tmdb.inject

import com.tikalk.tmdb.api.TmdbService
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    fun provideHttpClient(json: Json): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    fun provideMoviesService(client: HttpClient): TmdbService {
        return TmdbService(BASE_URL, client)
    }
}