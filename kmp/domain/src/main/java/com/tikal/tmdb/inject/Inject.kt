package com.tikal.tmdb.inject

object Inject {
    val getJson by lazy { NetworkModule.provideJson() }
    val getHttpClient by lazy { NetworkModule.provideHttpClient(getJson) }
    val getMoviesService by lazy { NetworkModule.provideMoviesService(getHttpClient) }
    val getLocalDataSource by lazy {
        ApplicationModule.provideLocalDataSource(getIODispatcher)
    }
    val getRemoteDataSource by lazy {
        ApplicationModule.provideRemoteDataSource(getMoviesService, getIODispatcher)
    }
    val getRepository by lazy {
        ApplicationModule.provideRepository(getLocalDataSource, getRemoteDataSource)
    }
    val getIODispatcher by lazy { ApplicationModule.provideIODispatcher() }
}