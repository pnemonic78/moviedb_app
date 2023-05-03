package com.tikal.tmdb.json.model

import java.util.Locale
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieImage(
    @SerialName("id")
    val id: String,
    @SerialName("iso_639_1")
    private val localeCountry: String? = null,
    @SerialName("iso_3166_1")
    private val localeLanguage: String? = null,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int,
    @SerialName("aspect_ratio")
    val aspectRatio: Double = 1.0,
    @SerialName("file_path")
    val path: String,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
) {
    val locale: Locale by lazy { Locale(localeLanguage.orEmpty(), localeCountry.orEmpty()) }
}
