package com.tikal.tmdb.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.UiState
import com.tikal.tmdb.model.MovieDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailsPage(
    mainState: UiState,
    uiState: MovieDetailsUiState,
    navController: NavController,
    movieId: Long
) {
    val movie by uiState.movieDetails(movieId).collectAsState()
    val coroutineScope = rememberCoroutineScope()

    if (movie != null) {
        LaunchedEffect(coroutineScope) {
            mainState.title.emit(movie!!.title)
        }
        MovieDetailsContent(movie!!)
    } else {
        Box(modifier = Modifier.background(color = Color.Red))
    }
}

@Preview
@Composable
private fun MovieDetailsPagePreview() {
    val mainState = object : UiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Main")
    }
    val detailsState = object : MovieDetailsUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Movie Details")
        override fun movieDetails(movieId: Long): StateFlow<MovieDetails?> =
            MutableStateFlow(movie550Details)
    }
    val navController = rememberNavController()
    MaterialTheme {
        MovieDetailsPage(mainState, detailsState, navController, movie550Details.id)
    }
}