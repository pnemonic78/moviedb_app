package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

data class PersonExternalIds(
    @ColumnInfo("id")
    val id: Long,

    /// IMDB ID
    @ColumnInfo("imdb_id")
    val imdbId: String? = null,

    /// Facebook
    @ColumnInfo("facebook_id")
    val facebookId: String? = null,

    /// Freebase MID
    @ColumnInfo("freebase_mid")
    val freebaseMid: String? = null,

    /// Freebase ID
    @ColumnInfo("freebase_id")
    val freebaseId: String? = null,

    /// Instagram
    @ColumnInfo("instagram_id")
    val instagramId: String? = null,

    /// TVRage ID
    @ColumnInfo("tvrage_id")
    val tvRageId: Long? = null,

    /// Twitter
    @ColumnInfo("twitter_id")
    val twitterId: String? = null
)
