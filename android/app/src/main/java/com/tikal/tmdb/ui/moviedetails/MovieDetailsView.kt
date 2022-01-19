package com.tikal.tmdb.ui.moviedetails

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tikal.tmdb.model.MovieDetails

@Composable
fun MovieDetailsView(uiState: MovieDetailsUiState) {
    val movie by uiState.movieDetails.observeAsState()

    MaterialTheme {
        movie?.let { MovieDetailsContent(it) }
    }
}

@Preview
@Composable
private fun MovieDetailsViewPreview() {
    val uiState = object : MovieDetailsUiState {
        override val isLoading: LiveData<Boolean> = MutableLiveData(false)
        override val movieDetails: LiveData<MovieDetails?> = MutableLiveData(movie550)
    }
    MovieDetailsView(uiState)
}