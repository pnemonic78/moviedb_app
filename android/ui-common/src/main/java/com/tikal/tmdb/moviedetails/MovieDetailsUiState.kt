package com.tikal.tmdb.moviedetails

import com.tikal.tmdb.UiState
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailsUiState : UiState {
    fun movieDetails(movieId: Long): StateFlow<MovieDetails?>
}