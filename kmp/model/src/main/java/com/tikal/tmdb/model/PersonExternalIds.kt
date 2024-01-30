package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonExternalIds(
    @SerialName("id")
    val id: Long,

    /// IMDB ID
    @SerialName("imdb_id")
    val imdbId: String? = null,

    /// Facebook
    @SerialName("facebook_id")
    val facebookId: String? = null,

    /// Freebase MID
    @SerialName("freebase_mid")
    val freebaseMid: String? = null,

    /// Freebase ID
    @SerialName("freebase_id")
    val freebaseId: String? = null,

    /// Instagram
    @SerialName("instagram_id")
    val instagramId: String? = null,

    /// TVRage ID
    @SerialName("tvrage_id")
    val tvRageId: Long? = null,

    /// Twitter
    @SerialName("twitter_id")
    val twitterId: String? = null
)
