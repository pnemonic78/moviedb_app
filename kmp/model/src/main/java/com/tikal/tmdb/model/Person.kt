package com.tikal.tmdb.model

import com.tikal.tmdb.json.DateTime
import com.tikal.tmdb.json.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Person POJO.
 */
@Serializable
open class Person : Media() {
    init {
        mediaType = MediaType.Person
    }

    @SerialName("also_known_as")
    var aliases: List<String> = emptyList()

    @SerialName("biography")
    var biography: String? = null

    @Serializable(with = DateTimeSerializer::class)
    @SerialName("birthday")
    var birthday: DateTime? = null

    @SerialName("combined_credits")
    var credits: PersonCredits? = null

    @SerialName("place_of_birth")
    var birthplace: String? = null

    @Serializable(with = DateTimeSerializer::class)
    @SerialName("deathday")
    var deathday: DateTime? = null

    @SerialName("external_ids")
    var externalIds: PersonExternalIds? = null

    @SerialName("gender")
    var gender: Gender = Gender.unknown

    @SerialName("homepage")
    var homepage: String? = null

    @SerialName("imdb_id")
    var imdbId: String? = null

    @SerialName("known_for_department")
    var knownDepartment: String? = null

    @SerialName("name")
    var name: String = ""

    @SerialName("original_name")
    var originalName: String = ""

    @SerialName("profile_path")
    var profilePath: String? = null

    override fun toString(): String {
        return name
    }
}