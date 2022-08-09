package com.tikal.tmdb.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @get:Query("SELECT * FROM movie")
    val all: Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id  = :id")
    fun getById(id: Long): Flow<MovieEntity>

    @Insert
    fun insertAll(vararg movies: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)
}