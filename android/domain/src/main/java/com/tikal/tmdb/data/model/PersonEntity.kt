package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tikal.tmdb.domain.DateTime

/**
 * Person entity.
 */
@Entity(tableName = "person")
data class PersonEntity(
    @ColumnInfo("adult")
    override val adult: Boolean,
    @ColumnInfo("id")
    @PrimaryKey
    override val id: Long,
    @ColumnInfo("media_type")
    override val mediaType: MediaType = MediaType.person,
    @ColumnInfo("popularity")
    override val popularity: Double = 0.0,
    @ColumnInfo("also_known_as")
    val aliases: List<String>,
    @ColumnInfo("biography")
    val biography: String,
    @ColumnInfo("birthday")
    val birthday: DateTime? = null,
    //TODO @ColumnInfo("combined_credits")
    //TODO val credits: PersonCreditsResponse,
    @ColumnInfo("place_of_birth")
    val birthplace: String,
    @ColumnInfo("deathday")
    val deathday: DateTime? = null,
    //TODO @ColumnInfo("external_ids")
    //TODO val externalIds: PersonExternalIds,
    @ColumnInfo("gender")
    val gender: Gender,
    @ColumnInfo("homepage")
    val homepage: String,
    @ColumnInfo("imdb_id")
    val imdbId: String,
    @ColumnInfo("known_for_department")
    val knownDepartment: String,
    @ColumnInfo("name")
    override val name: String,
    @ColumnInfo("original_name")
    val originalName: String,
    @ColumnInfo("profile_path")
    val profilePath: String
) : MediaEntity()