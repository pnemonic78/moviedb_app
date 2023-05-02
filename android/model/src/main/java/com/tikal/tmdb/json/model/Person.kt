package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.DateTimeSerializer
import com.tikal.tmdb.json.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Person POJO.
 */
@Serializable
data class Person(
    @SerialName("adult")
    override val adult: Boolean = false,
    @SerialName("id")
    override val id: Long,
    @SerialName("media_type")
    override val mediaType: MediaType = MediaType.person,
    @SerialName("popularity")
    override val popularity: Double = 0.0,
    @SerialName("also_known_as")
    val aliases: List<String> ,
    @SerialName("biography")
    val biography: String,
    @Serializable(with = DateTimeSerializer::class)
    @SerialName("birthday")
    val birthday: DateTime? = null,
    //TODO @SerialName("combined_credits")
    //TODO val credits: PersonCreditsResponse,
    @SerialName("place_of_birth")
    val birthplace: String,
    @Serializable(with = DateTimeSerializer::class)
    @SerialName("deathday")
    val deathday: DateTime? = null,
    //TODO @SerialName("external_ids")
    //TODO val externalIds: PersonExternalIds,
    @SerialName("gender")
    val gender: Gender,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("known_for_department")
    val knownDepartment: String,
    @SerialName("name")
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("profile_path")
    val profilePath: String
) : Media()