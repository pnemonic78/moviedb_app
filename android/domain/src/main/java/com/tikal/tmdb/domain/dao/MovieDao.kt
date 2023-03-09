package com.tikal.tmdb.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao : BaseDao<MovieEntity> {

    @get:Query("SELECT * FROM movie")
    val all: Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE (id = :id)")
    suspend fun getById(id: Long): MovieEntity?

    /**
     * Delete all records.
     */
    @Query("DELETE FROM movie")
    suspend fun deleteAll(): Int
}