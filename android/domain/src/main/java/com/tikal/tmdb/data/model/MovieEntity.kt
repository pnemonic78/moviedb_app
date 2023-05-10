package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.tikal.tmdb.domain.DateTime

/**
 * Movie entity.
 */
@Entity(tableName = "movie")
class MovieEntity : MediaEntity() {
    init {
        mediaType = MediaType.movie
    }

    @ColumnInfo("backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo("genre_ids")
    var genreIds: LongArray = longArrayOf()

    @ColumnInfo("original_language")
    var originalLanguage: String = ""

    @ColumnInfo("original_title")
    var originalTitle: String = ""

    @ColumnInfo("overview")
    var overview: String? = null

    @ColumnInfo("poster_path")
    var posterPath: String? = null

    @ColumnInfo("release_date")
    var releaseDate: DateTime? = null

    @ColumnInfo("title")
    var title: String = ""

    @ColumnInfo("video")
    var video: Boolean = false

    @ColumnInfo("vote_average")
    var voteAverage: Double = 0.0

    @ColumnInfo("vote_count")
    var voteCount: Int = 0

    @ColumnInfo("budget")
    var budget: Long = 0

    @ColumnInfo("homepage")
    var homepage: String? = null

    @ColumnInfo("imdb_id")
    var imdbId: String? = null

    // TODO @ColumnInfo("production_companies")
    // TODO var productionCompanies: List<ProductionCompanyEntity> = emptyList()

    // TODO @ColumnInfo("production_countries")
    // TODO var productionCountries: List<ProductionCountryEntity> = emptyList()

    @ColumnInfo("revenue")
    var revenue: Long? = null

    @ColumnInfo("runtime")
    var runtime: Int? = null

    // TODO @ColumnInfo("spoken_languages")
    // TODO var spokenLanguages: List<SpokenLanguageEntity> = emptyList()

    @ColumnInfo("status")
    var status: String = ""

    @ColumnInfo("tagline")
    var tagline: String? = null

    val name: String
        get() = originalTitle

    @Ignore
    var genres: List<GenreEntity>? = null
        set(value) {
            field = value
            if (value != null) {
                genreIds = value.map { it.id }.toLongArray()
            }
        }
}