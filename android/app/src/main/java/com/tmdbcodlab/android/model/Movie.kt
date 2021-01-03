package com.tmdbcodlab.android.model

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * Created by ronelg on 12/19/17.
 */
data class Movie(@SerializedName("id") var id: Long,
                 @SerializedName("adult") var adult: Boolean,
                 @SerializedName("backdrop_path") var backdropPath: String?,
                 @SerializedName("genre_ids") var genreIds: List<Long>,
                 @SerializedName("original_language") var originalLanguage: String,
                 @SerializedName("original_title") var originalTitle: String,
                 @SerializedName("overview") var overview: String?,
                 @SerializedName("popularity") var popularity: Float,
                 @SerializedName("poster_path") var posterPath: String?,
                 @SerializedName("release_date") var releaseDate: Date,
                 @SerializedName("title") var title: String,
                 @SerializedName("video") var video: Boolean,
                 @SerializedName("vote_average") var voteAverage: Float,
                 @SerializedName("vote_count") var voteCount: Int)