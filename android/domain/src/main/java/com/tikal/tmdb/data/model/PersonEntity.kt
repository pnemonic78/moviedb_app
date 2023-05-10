package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.tikal.tmdb.domain.DateTime

/**
 * Person entity.
 */
@Entity(tableName = "person")
open class PersonEntity : MediaEntity() {
    init {
        mediaType = MediaType.person
    }

    @ColumnInfo("also_known_as")
    var aliases: List<String> = emptyList()

    @ColumnInfo("biography")
    var biography: String? = null

    @ColumnInfo("birthday")
    var birthday: DateTime? = null

    //TODO @ColumnInfo("combined_credits")
    //TODO var credits: PersonCreditsResponse? = null

    @ColumnInfo("place_of_birth")
    var birthplace: String? = null

    @ColumnInfo("deathday")
    var deathday: DateTime? = null

    @Embedded(prefix = "external_id_")
    var externalIds: PersonExternalIds? = null

    @ColumnInfo("gender")
    var gender: Gender = Gender.unknown

    @ColumnInfo("homepage")
    var homepage: String? = null

    @ColumnInfo("imdb_id")
    var imdbId: String? = null

    @ColumnInfo("known_for_department")
    var knownDepartment: String? = null

    @ColumnInfo("name")
    var name: String = ""

    @ColumnInfo("original_name")
    var originalName: String = ""

    @ColumnInfo("profile_path")
    var profilePath: String? = null
}