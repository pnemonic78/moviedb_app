package com.tikal.tmdb.moviedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
@Deprecated("needs nav")
fun MovieDetailsPage(uiState: MovieDetailsUiState) {
    val navController = rememberNavController()
    MovieDetailsPage(uiState, navController)
}

@Composable
fun MovieDetailsPage(uiState: MovieDetailsUiState, navController: NavController) {
    val movie by uiState.movieDetails.collectAsState()

    if (movie != null) {
        MovieDetailsContent(movie!!)
    } else {
        Box(modifier = Modifier)
    }
}

@Preview
@Composable
private fun MovieDetailsPagePreview() {
    val uiState = object : MovieDetailsUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow<Boolean>(false)
        override val movieDetails: StateFlow<MovieDetails?> = MutableStateFlow(movie550Details)
    }
    val navController = rememberNavController()
    MaterialTheme {
        MovieDetailsPage(uiState, navController)
    }
}