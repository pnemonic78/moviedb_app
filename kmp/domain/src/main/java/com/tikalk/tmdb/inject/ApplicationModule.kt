package com.tikalk.tmdb.inject

import com.tikalk.tmdb.api.TmdbService
import com.tikalk.tmdb.data.TmdbRepository
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.data.source.local.TmdbLocalDataSource
import com.tikalk.tmdb.data.source.remote.TmdbRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object ApplicationModule {

    fun provideLocalDataSource(
        ioDispatcher: CoroutineDispatcher
    ): TmdbLocalDataSource = TmdbLocalDataSource(ioDispatcher)

    fun provideRemoteDataSource(
        service: TmdbService,
        ioDispatcher: CoroutineDispatcher
    ): TmdbRemoteDataSource = TmdbRemoteDataSource(service, ioDispatcher)

    fun provideRepository(
        local: TmdbLocalDataSource,
        remote: TmdbRemoteDataSource
    ): TmdbDataSource = TmdbRepository(local, remote)

    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}