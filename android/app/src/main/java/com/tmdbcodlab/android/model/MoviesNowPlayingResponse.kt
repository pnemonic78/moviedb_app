package com.tmdbcodlab.android.model

import com.google.gson.annotations.SerializedName

/**
 * Response for Movies that are Now Playing.
 */
class MoviesNowPlayingResponse(
        @SerializedName("results") val results: List<Movie>,
        @SerializedName("dates") val dates: Dates,
        @SerializedName("page") val page: Int,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("total_results") val totalResult: Int)