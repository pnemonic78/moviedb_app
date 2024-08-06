package com.tikalk.tmdb.ui.movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tikalk.tmdb.movies.OnMovieClickCallback
import com.tikalk.tmdb.ui.MoviesViewModel

@Composable
fun MoviesRoute(
    viewModel: MoviesViewModel,
    onClick: OnMovieClickCallback
) {
    val uiState by viewModel.uiState.collectAsState()
    MoviesScreen(
        uiState = uiState,
        onClick = onClick
    )
}
