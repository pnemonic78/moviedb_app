package com.tikal.tmdb.ui.movies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.MovieListTile
import com.tikal.tmdb.R
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.json.model.Movie
import com.tikal.tmdb.movie550
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesListPage(uiState: MoviesListState, navController: NavController) {
    val movies by uiState.movies.collectAsState()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val title = stringResource(id = R.string.now_playing)
    LaunchedEffect(coroutineScope) {
        uiState.title.emit(title)
    }

    LazyColumn(
        state = scrollState
    ) {
        items(items = movies) { movie ->
            MovieListTile(movie, onMovieClicked = { uiState.onMovieClicked(movie, navController) })
        }
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val listState = object : MoviesListState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Movies List")
        override val movies: StateFlow<List<MovieEntity>> = MutableStateFlow(listOf(movie550, movie550))
        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
    }
    val navController = rememberNavController()
    MaterialTheme {
        MoviesListPage(listState, navController)
    }
}