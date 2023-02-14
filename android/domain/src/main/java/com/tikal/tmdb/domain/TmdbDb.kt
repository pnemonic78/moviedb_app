package com.tikal.tmdb.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tikal.tmdb.data.model.GenreEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPageCrossRef
import com.tikal.tmdb.data.model.MoviesPageEntity
import com.tikal.tmdb.data.model.ProductionCompanyEntity
import com.tikal.tmdb.data.model.ProductionCountryEntity
import com.tikal.tmdb.data.model.SpokenLanguageEntity
import com.tikal.tmdb.data.model.TrailerEntity
import com.tikal.tmdb.domain.dao.GenreDao
import com.tikal.tmdb.domain.dao.MovieDao
import com.tikal.tmdb.domain.dao.MoviesPageCrossRefDao
import com.tikal.tmdb.domain.dao.MoviesPageDao

@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class,
        MoviesPageCrossRef::class,
        MoviesPageEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class,
        TrailerEntity::class
    ],
    exportSchema = false,
    version = 4
)
@TypeConverters(Converters::class)
abstract class TmdbDb : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun moviePagesDao(): MoviesPageDao
    abstract fun moviesPageCrossRefDao(): MoviesPageCrossRefDao
}