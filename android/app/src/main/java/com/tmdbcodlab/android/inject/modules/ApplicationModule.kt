package com.tmdbcodlab.android.inject.modules

import android.content.Context
import com.tmdbcodlab.android.api.TmdbService
import com.tmdbcodlab.android.data.TmdbRepository
import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.data.source.local.TmdbLocalDataSource
import com.tmdbcodlab.android.data.source.remote.TmdbRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): TmdbLocalDataSource =
        TmdbLocalDataSource(context)

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