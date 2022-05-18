package com.tikal.tmdb.moviedetails

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailsView(uiState: MovieDetailsUiState) {
    val movie by uiState.movieDetails.collectAsState()

    MaterialTheme {
        movie?.let { MovieDetailsContent(it) }
    }
}

@Preview
@Composable
private fun MovieDetailsViewPreview() {
    val uiState = object : MovieDetailsUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow<Boolean>(false)
        override val movieDetails: StateFlow<MovieDetails?> = MutableStateFlow(movie550)
    }
    MovieDetailsView(uiState)
}