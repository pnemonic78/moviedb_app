package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Calendar

/**
 * Movie entity.
 */
@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    override val id: Long,

    @ColumnInfo(name = "adult")
    override val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "genre_ids")
    var genreIds: LongArray = longArrayOf(),

    @ColumnInfo(name = "media_type")
    override val mediaType: MediaType = MediaType.movie,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    override val popularity: Double,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: Calendar? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "video")
    val video: Boolean = false,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int = 0,

    @ColumnInfo(name = "budget")
    val budget: Long = 0,

    @ColumnInfo(name = "homepage")
    val homepage: String? = null,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String? = null,

    @ColumnInfo(name = "revenue")
    val revenue: Long? = null,

    @ColumnInfo(name = "runtime")
    val runtime: Int? = null,

    @ColumnInfo(name = "status")
    val status: String = "",

    @ColumnInfo(name = "tagline")
    val tagline: String? = null
) : MediaEntity() {
    // TODO val credits: CreditsResponseEntity? =null,

    @Ignore
    var genres: List<GenreEntity>? = null
        set(value) {
            field = value
            if (value != null) {
                genreIds = value.map { it.id }.toLongArray()
            }
        }

//    TODO val productionCompanies: List<ProductionCompanyEntity>? = null,

//    TODO val productionCountries: List<ProductionCountryEntity>? = null,

//    TODO val spokenLanguages: List<SpokenLanguageEntity>? = null,

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieEntity

        if (id != other.id) return false
        if (originalTitle != other.originalTitle) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + originalTitle.hashCode()
        return result
    }
}