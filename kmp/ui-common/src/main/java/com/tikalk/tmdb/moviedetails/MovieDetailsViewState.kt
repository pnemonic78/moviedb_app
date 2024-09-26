package com.tikalk.tmdb.moviedetails

import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ui.ViewState

data class MovieDetailsViewState(
    override val isLoading: Boolean = false,
    val movie: MovieEntity? = null
): ViewState
