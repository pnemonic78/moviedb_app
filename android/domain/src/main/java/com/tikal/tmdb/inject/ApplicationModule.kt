package com.tikal.tmdb.inject

import android.content.Context
import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.TmdbRepository
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.data.source.local.TmdbLocalDataSource
import com.tikal.tmdb.data.source.remote.TmdbRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context,
        json: Json
    ): TmdbLocalDataSource = TmdbLocalDataSource(context, json)

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: TmdbService): TmdbRemoteDataSource =
        TmdbRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideRepository(
        local: TmdbLocalDataSource,
        remote: TmdbRemoteDataSource
    ): TmdbDataSource = TmdbRepository(local, remote)
}