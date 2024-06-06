package com.tikal.tmdb.json.model

import java.util.Locale
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("id")
    val id: String,
    @SerialName("iso_639_1")
    private val localeCountry: String? = null,
    @SerialName("iso_3166_1")
    private val localeLanguage: String? = null,
    @SerialName("key")
    val key: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("site")
    val site: String?,
    @SerialName("size")
    val size: Int = 0,
    @SerialName("type")
    val type: VideoType?
) {
    val locale: Locale by lazy { Locale(localeLanguage.orEmpty(), localeCountry.orEmpty()) }
}
