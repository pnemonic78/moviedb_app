package com.tikal.tmdb.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.tikal.tmdb.data.model.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao : BaseDao<GenreEntity> {

    @get:Query("SELECT * FROM genre")
    val all: Flow<List<GenreEntity>>

    @Query("SELECT * FROM genre WHERE (id = :id)")
    fun getById(id: Long): GenreEntity

    @Query("SELECT * FROM genre WHERE (id IN (:ids))")
    fun getByIds(ids: LongArray): List<GenreEntity>

    /**
     * Delete all records.
     */
    @Query("DELETE FROM genre")
    fun deleteAll(): Int
}