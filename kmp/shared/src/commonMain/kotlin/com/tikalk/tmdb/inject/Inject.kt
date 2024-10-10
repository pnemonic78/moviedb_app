package com.tikalk.tmdb.inject

import com.tikalk.tmdb.api.TmdbService
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.data.source.local.TmdbLocalDataSource
import com.tikalk.tmdb.data.source.remote.TmdbRemoteDataSource
import com.tikalk.tmdb.domain.TmdbDb
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json

object Inject {
    val ioDispatcher: CoroutineDispatcher by lazy { ApplicationModule.provideIODispatcher() }
    val json: Json by lazy { NetworkModule.provideJson() }
    val httpClient: HttpClient by lazy { NetworkModule.provideHttpClient(json) }
    val moviesService: TmdbService by lazy { NetworkModule.provideMoviesService(httpClient) }
    val db: TmdbDb by lazy { ApplicationModule.provideDatabase(ioDispatcher) }
    val localDataSource: TmdbLocalDataSource by lazy {
        ApplicationModule.provideLocalDataSource(db, ioDispatcher)
    }
    val remoteDataSource: TmdbRemoteDataSource by lazy {
        ApplicationModule.provideRemoteDataSource(moviesService, db, ioDispatcher)
    }
    val repository: TmdbDataSource by lazy {
        ApplicationModule.provideRepository(localDataSource, remoteDataSource)
    }
}