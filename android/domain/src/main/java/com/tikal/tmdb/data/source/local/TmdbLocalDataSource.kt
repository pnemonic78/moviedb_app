package com.tikal.tmdb.data.source.local

import android.content.Context
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * TMDB local data source.
 */
class TmdbLocalDataSource @Inject constructor(
    @ApplicationContext val context: Context
) : TmdbDataSource {

    override suspend fun getMoviesNowPlaying(): Flow<List<MovieEntity>> {
        // TODO use Room DB.
        return flowOf()
    }

    override suspend fun getMovie(movieId: Long): Flow<MovieEntity> {
        // TODO use Room DB.
        return flowOf()
    }
}