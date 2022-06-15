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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.model.MovieDetails
import com.tikal.tmdb.ui.common.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailsPage(
    uiState: MovieDetailsUiState,
    navController: NavController,
    movieId: Long
) {
    val movie by uiState.movieDetails(movieId).collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val title = stringResource(id = R.string.movie_details)
    LaunchedEffect(coroutineScope) {
        uiState.title.emit(title)
    }

    if (movie != null) {
        MovieDetailsContent(movie!!)
    } else {
        Box(modifier = Modifier.background(color = Color.Red))
    }
}

@Preview
@Composable
private fun MovieDetailsPagePreview() {
    val uiState = object : MovieDetailsUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Movie Details")
        override fun movieDetails(movieId: Long): StateFlow<MovieDetails?> =
            MutableStateFlow(movie550Details)
    }
    val navController = rememberNavController()
    MaterialTheme {
        MovieDetailsPage(uiState, navController, movie550Details.id)
    }
}