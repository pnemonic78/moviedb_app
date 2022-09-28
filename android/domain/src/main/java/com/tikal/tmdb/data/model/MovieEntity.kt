package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.Calendar

/**
 * Movie entity.
 */
@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "genre_ids")
    val genreIds: LongArray = longArrayOf(),

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Float,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: Calendar? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "video")
    val video: Boolean = false,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Float,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int,

    @ColumnInfo(name = "budget")
    val budget: Long = 0,

    // TODO @JsonKey(name: 'credits')
    // TODO val credits: CreditsResponseEntity? =null,

    //TODO @JsonKey(name: 'genres')
    //TODO val genres: List<GenreEntity>?= null,

    @ColumnInfo(name = "homepage")
    val homepage: String? = null,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String? = null,

//    val productionCompanies: List<ProductionCompanyEntity>? = null,

//    val productionCountries: List<ProductionCountryEntity>? = null,

    @ColumnInfo(name = "revenue")
    val revenue: Long? = null,

    @ColumnInfo(name = "runtime")
    val runtime: Int? = null,

//    val spokenLanguages: List<SpokenLanguageEntity>? = null,

    @ColumnInfo(name = "status")
    val status: String = "",

    @ColumnInfo(name = "tagline")
    val tagline: String? = null
)