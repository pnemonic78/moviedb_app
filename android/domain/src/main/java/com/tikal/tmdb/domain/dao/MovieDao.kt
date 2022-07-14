package com.tikal.tmdb.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tikal.tmdb.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @get:Query("SELECT * FROM movie")
    val all: Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id  = :id")
    fun getById(id: Long): Flow<Movie>

    @Insert
    fun insertAll(vararg movies: Movie)

    @Delete
    fun delete(movie: Movie)
}