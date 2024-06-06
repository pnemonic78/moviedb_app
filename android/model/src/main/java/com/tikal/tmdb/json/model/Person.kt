package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.DateTime
import com.tikal.tmdb.json.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Person POJO.
 */
@Serializable
open class Person : Media() {
    @SerialName("adult")
    override var adult: Boolean = false

    @SerialName("id")
    override var id: Long = 0

    @SerialName("media_type")
    override var mediaType: MediaType = MediaType.person

    @SerialName("popularity")
    override var popularity: Double = 0.0

    @SerialName("also_known_as")
    var aliases: List<String> = emptyList()

    @SerialName("biography")
    var biography: String? = null

    @Serializable(with = DateTimeSerializer::class)
    @SerialName("birthday")
    var birthday: DateTime? = null

    @SerialName("combined_credits")
    var credits: PersonCreditsResponse? = null

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
}