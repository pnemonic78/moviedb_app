package com.tikalk.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response for a page of movies.
 */
@Serializable
data class MoviesPageResponse(
    @SerialName("results")
    val results: List<Movie>,

    @SerialName("dates")
    val dates: Dates? = null,

    @SerialName("page")
    val page: Int,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)