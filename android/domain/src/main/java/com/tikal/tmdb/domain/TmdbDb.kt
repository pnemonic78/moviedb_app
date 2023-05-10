package com.tikal.tmdb.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tikal.tmdb.data.model.CastEntity
import com.tikal.tmdb.data.model.CreditEntity
import com.tikal.tmdb.data.model.CrewEntity
import com.tikal.tmdb.data.model.GenreEntity
import com.tikal.tmdb.data.model.MediaImageEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPageCrossRef
import com.tikal.tmdb.data.model.MoviesPageEntity
import com.tikal.tmdb.data.model.PersonEntity
import com.tikal.tmdb.data.model.ProductionCompanyEntity
import com.tikal.tmdb.data.model.ProductionCountryEntity
import com.tikal.tmdb.data.model.SpokenLanguageEntity
import com.tikal.tmdb.data.model.TelevisionEntity
import com.tikal.tmdb.data.model.VideoEntity
import com.tikal.tmdb.domain.dao.GenreDao
import com.tikal.tmdb.domain.dao.MovieDao
import com.tikal.tmdb.domain.dao.MoviesPageCrossRefDao
import com.tikal.tmdb.domain.dao.MoviesPageDao

@Database(
    entities = [
        CastEntity::class,
        CreditEntity::class,
        CrewEntity::class,
        GenreEntity::class,
        MediaImageEntity::class,
        MovieEntity::class,
        MoviesPageCrossRef::class,
        MoviesPageEntity::class,
        PersonEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class,
        TelevisionEntity::class,
        VideoEntity::class
    ],
    exportSchema = false,
    version = 6
)
@TypeConverters(Converters::class)
abstract class TmdbDb : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun moviePagesDao(): MoviesPageDao
    abstract fun moviesPageCrossRefDao(): MoviesPageCrossRefDao
}