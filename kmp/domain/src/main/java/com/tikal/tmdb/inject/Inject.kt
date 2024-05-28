package com.tikal.tmdb.inject

object Inject {
    val json by lazy { NetworkModule.provideJson() }
    val httpClient by lazy { NetworkModule.provideHttpClient(json) }
    val moviesService by lazy { NetworkModule.provideMoviesService(httpClient) }
    val localDataSource by lazy {
        ApplicationModule.provideLocalDataSource(ioDispatcher)
    }
    val remoteDataSource by lazy {
        ApplicationModule.provideRemoteDataSource(moviesService, ioDispatcher)
    }
    val repository by lazy {
        ApplicationModule.provideRepository(localDataSource, remoteDataSource)
    }
    val ioDispatcher by lazy { ApplicationModule.provideIODispatcher() }
}