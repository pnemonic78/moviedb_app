package com.tikal.tmdb.ui.movies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
@Deprecated("use nav")
fun MoviesListPage(uiState: MoviesUiState) {
    val navController = rememberNavController()
    MoviesListPage(uiState, navController)
}

@Composable
fun MoviesListPage(uiState: MoviesUiState, navController: NavController) {
    val movies by uiState.movies.collectAsState()
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState
    ) {
        items(items = movies.orEmpty()) { movie ->
            MovieListTile(movie, onMovieClicked = { uiState.onMovieClicked(movie, navController) })
        }
    }
}

@Preview
@Composable
private fun MoviesListPagePreview() {
    val uiState = object : MoviesUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val movies: StateFlow<List<Movie>?> = MutableStateFlow(listOf(movie550, movie550))
        override fun onMovieClicked(movie: Movie, navController: NavController) = Unit
    }
    val navController = rememberNavController()
    MaterialTheme {
        MoviesListPage(uiState, navController)
    }
}