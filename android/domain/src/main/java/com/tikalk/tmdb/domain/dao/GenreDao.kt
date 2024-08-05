package com.tikalk.tmdb.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.tikalk.tmdb.data.model.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao : BaseDao<GenreEntity> {

    @get:Query("SELECT * FROM genre")
    val all: Flow<List<GenreEntity>>

    @Query("SELECT * FROM genre WHERE (id = :id)")
    suspend fun getById(id: Long): GenreEntity

    @Query("SELECT * FROM genre WHERE (id IN (:ids))")
    suspend fun getByIds(ids: LongArray): List<GenreEntity>

    /**
     * Delete all records.
     */
    @Query("DELETE FROM genre")
    suspend fun deleteAll(): Int
}