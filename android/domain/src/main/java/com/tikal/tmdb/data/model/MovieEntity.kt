package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.tikal.tmdb.domain.DateTime

/**
 * Movie entity.
 */
@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo("id")
    @PrimaryKey
    override val id: Long,

    @ColumnInfo("adult")
    override val adult: Boolean,

    @ColumnInfo("backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo("genre_ids")
    var genreIds: LongArray = longArrayOf(),

    @ColumnInfo("media_type")
    override val mediaType: MediaType = MediaType.movie,

    @ColumnInfo("original_language")
    val originalLanguage: String,

    @ColumnInfo("original_title")
    val originalTitle: String,

    @ColumnInfo("overview")
    val overview: String?,

    @ColumnInfo("popularity")
    override val popularity: Double,

    @ColumnInfo("poster_path")
    val posterPath: String? = null,

    @ColumnInfo("release_date")
    val releaseDate: DateTime? = null,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("video")
    val video: Boolean = false,

    @ColumnInfo("vote_average")
    val voteAverage: Double = 0.0,

    @ColumnInfo("vote_count")
    val voteCount: Int = 0,

    @ColumnInfo("budget")
    val budget: Long = 0,

    @ColumnInfo("homepage")
    val homepage: String? = null,

    @ColumnInfo("imdb_id")
    val imdbId: String? = null,

    @ColumnInfo("revenue")
    val revenue: Long? = null,

    @ColumnInfo("runtime")
    val runtime: Int? = null,

    @ColumnInfo("status")
    val status: String = "",

    @ColumnInfo("tagline")
    val tagline: String? = null
) : MediaEntity() {

    override val name: String
        get() = originalTitle

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
}