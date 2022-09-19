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
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.ui.common.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun MoviePosterPage(
    uiState: MovieDetailsUiState,
    movieId: Long
) {
    val movieState by uiState.movieDetails(movieId).collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var title = stringResource(id = R.string.movie_details)
    LaunchedEffect(coroutineScope) {
        uiState.title.emit(title)
    }

    if (movieState != null) {
        val movie = movieState!!
        title = movie.title
        coroutineScope.launch { uiState.title.emit(title) }
        MoviePosterContent(movie = movie)
    } else {
        Box(modifier = Modifier.background(color = Color.Red))
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val uiState = object : MovieDetailsUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Movie Details")
        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            MutableStateFlow(movie550Details)

        override fun onPosterClicked(movie: MovieEntity, navController: NavController) = Unit
    }
    MaterialTheme {
        MoviePosterPage(uiState, movie550Details.id)
    }
}