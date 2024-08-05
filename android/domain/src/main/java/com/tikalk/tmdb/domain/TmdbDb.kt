package com.tikalk.tmdb.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tikalk.tmdb.data.model.CastEntity
import com.tikalk.tmdb.data.model.CreditEntity
import com.tikalk.tmdb.data.model.CrewEntity
import com.tikalk.tmdb.data.model.GenreEntity
import com.tikalk.tmdb.data.model.MediaImageEntity
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPageCrossRef
import com.tikalk.tmdb.data.model.MoviesPageEntity
import com.tikalk.tmdb.data.model.PersonEntity
import com.tikalk.tmdb.data.model.ProductionCompanyEntity
import com.tikalk.tmdb.data.model.ProductionCountryEntity
import com.tikalk.tmdb.data.model.SpokenLanguageEntity
import com.tikalk.tmdb.data.model.TelevisionEntity
import com.tikalk.tmdb.data.model.VideoEntity
import com.tikalk.tmdb.domain.dao.GenreDao
import com.tikalk.tmdb.domain.dao.MovieDao
import com.tikalk.tmdb.domain.dao.MoviesPageCrossRefDao
import com.tikalk.tmdb.domain.dao.MoviesPageDao

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