package com.tikal.tmdb.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tikal.tmdb.data.model.GenreEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesNowPlayingEntity
import com.tikal.tmdb.data.model.ProductionCompanyEntity
import com.tikal.tmdb.data.model.ProductionCountryEntity
import com.tikal.tmdb.data.model.SpokenLanguageEntity
import com.tikal.tmdb.data.model.TrailerEntity
import com.tikal.tmdb.domain.dao.MovieDao

@Database(
    entities = [
//        DatesEntity::class,
        GenreEntity::class,
//        MovieDetails::class,
        MovieEntity::class,
//        MovieProductionCompanyJoin::class,
//        MovieProductionCountryJoin::class,
        MoviesNowPlayingEntity::class,
//        MovieSpokenLanguageJoin::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class,
        TrailerEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class TmdbDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}