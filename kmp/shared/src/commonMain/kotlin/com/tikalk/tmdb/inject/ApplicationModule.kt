package com.tikalk.tmdb.inject

import com.tikalk.tmdb.api.TmdbService
import com.tikalk.tmdb.data.TmdbRepository
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.data.source.local.TmdbLocalDataSource
import com.tikalk.tmdb.data.source.remote.TmdbRemoteDataSource
import com.tikalk.tmdb.domain.TmdbDb
import com.tikalk.tmdb.getPlatform
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object ApplicationModule {

    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    fun provideDatabase(ioDispatcher: CoroutineDispatcher): TmdbDb = getPlatform().databaseBuilder
        .fallbackToDestructiveMigration(true)
        .setQueryCoroutineContext(ioDispatcher)
        .build()

    fun provideLocalDataSource(
        db: TmdbDb,
        ioDispatcher: CoroutineDispatcher
    ): TmdbLocalDataSource = TmdbLocalDataSource(db, ioDispatcher)

    fun provideRemoteDataSource(
        service: TmdbService,
        db: TmdbDb,
        ioDispatcher: CoroutineDispatcher
    ): TmdbRemoteDataSource = TmdbRemoteDataSource(service, db, ioDispatcher)

    fun provideRepository(
        local: TmdbLocalDataSource,
        remote: TmdbRemoteDataSource
    ): TmdbDataSource = TmdbRepository(local, remote)
}