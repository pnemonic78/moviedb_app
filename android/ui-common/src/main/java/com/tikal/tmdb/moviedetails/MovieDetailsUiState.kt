package com.tikal.tmdb.moviedetails

import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailsUiState {
    val isLoading: StateFlow<Boolean>
    fun movieDetails(movieId: Long): StateFlow<MovieDetails?>
}