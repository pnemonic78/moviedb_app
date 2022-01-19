package com.tikal.tmdb.ui.moviedetails

import androidx.lifecycle.LiveData
import com.tikal.tmdb.model.MovieDetails

interface MovieDetailsUiState {
    val isLoading: LiveData<Boolean>
    val movieDetails: LiveData<MovieDetails?>
}