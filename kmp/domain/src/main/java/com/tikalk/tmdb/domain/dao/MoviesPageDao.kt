package com.tikalk.tmdb.domain.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tikalk.tmdb.data.model.MoviesPage
import com.tikalk.tmdb.data.model.MoviesPageEntity
import com.tikalk.tmdb.data.model.MoviesPageType
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesPageDao : BaseDao<MoviesPageEntity> {

    @get:Query("SELECT * FROM movies_page ORDER BY type ASC, page ASC")
    val all: Flow<List<MoviesPageEntity>>

    @Transaction
    @Query("SELECT * FROM movies_page WHERE (type = :type) ORDER BY page ASC")
    fun getByType(type: MoviesPageType): Flow<List<MoviesPage>>

    @Transaction
    @Query("SELECT * FROM movies_page WHERE (type = :type) AND (page = :page)")
    suspend fun getByPage(type: MoviesPageType, page: Int): MoviesPage?

    suspend fun getNowPlaying() = getByType(MoviesPageType.now_playing)

    suspend fun getNowPlaying(page: Int = 1) = getByPage(MoviesPageType.now_playing, page)

    suspend fun getPopular() = getByType(MoviesPageType.popular)

    suspend fun getPopular(page: Int = 1) = getByPage(MoviesPageType.popular, page)

    suspend fun getTopRated() = getByType(MoviesPageType.top_rated)

    suspend fun getTopRated(page: Int) = getByPage(MoviesPageType.top_rated, page)

    suspend fun getUpcoming() = getByType(MoviesPageType.upcoming)

    suspend fun getUpcoming(page: Int) = getByPage(MoviesPageType.upcoming, page)

    /**
     * Delete all records.
     */
    @Query("DELETE FROM movies_page")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM movies_page WHERE (type = :type)")
    suspend fun deleteByType(type: MoviesPageType): Int

    suspend fun deleteNowPlaying() = deleteByType(MoviesPageType.now_playing)

    suspend fun deletePopular() = deleteByType(MoviesPageType.popular)

    suspend fun deleteTopRated() = deleteByType(MoviesPageType.top_rated)

    suspend fun deleteUpcoming() = deleteByType(MoviesPageType.upcoming)
}