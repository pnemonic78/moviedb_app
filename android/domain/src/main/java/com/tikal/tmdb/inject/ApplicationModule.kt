package com.tikal.tmdb.inject

import android.content.Context
import androidx.room.Room
import com.tikal.tmdb.api.TmdbService
import com.tikal.tmdb.data.TmdbRepository
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.data.source.local.TmdbLocalDataSource
import com.tikal.tmdb.data.source.remote.TmdbRemoteDataSource
import com.tikal.tmdb.domain.TmdbDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TmdbDb = Room.databaseBuilder(
        context,
        TmdbDb::class.java, "tmdb"
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        db: TmdbDb,
        ioDispatcher: CoroutineDispatcher
    ): TmdbLocalDataSource = TmdbLocalDataSource(db, ioDispatcher)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        service: TmdbService,
        db: TmdbDb,
        ioDispatcher: CoroutineDispatcher
    ): TmdbRemoteDataSource = TmdbRemoteDataSource(service, db, ioDispatcher)

    @Provides
    @Singleton
    fun provideRepository(
        local: TmdbLocalDataSource,
        remote: TmdbRemoteDataSource
    ): TmdbDataSource = TmdbRepository(local, remote)

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}