package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response for Movies that are Now Playing.
 */
@Serializable
class MoviesNowPlayingResponse(
    @SerialName("results") val results: List<Movie>,
    @SerialName("dates") val dates: Dates,
    @SerialName("page") val page: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResult: Int
)