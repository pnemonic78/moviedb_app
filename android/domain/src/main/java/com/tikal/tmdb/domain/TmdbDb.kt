package com.tikal.tmdb.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tikal.tmdb.domain.dao.MovieDao
import com.tikal.tmdb.model.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(Converters::class)
abstract class TmdbDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}