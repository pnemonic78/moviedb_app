package com.tikal.tmdb.ui.moviedetails

import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailsUiState {
    val isLoading: StateFlow<Boolean>
    val movieDetails: StateFlow<MovieDetails?>
}