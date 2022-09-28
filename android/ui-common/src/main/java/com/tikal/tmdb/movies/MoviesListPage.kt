package com.tikal.tmdb.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.MovieListTile
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.movie550
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesListPage(title: String, uiState: MoviesPageState, navController: NavController) {
    val movies by uiState.movies.collectAsState()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(coroutineScope) {
        uiState.title.emit(title)
    }

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = movies.size) { index ->
            val movie = movies[index]
            MovieListTile(movie, onMovieClicked = { uiState.onMovieClicked(movie, navController) })
        }
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val title = "List Page"
    val listState = object : MoviesPageState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Movies List")
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(false)
        override val movies: StateFlow<List<MovieEntity>> =
            MutableStateFlow(listOf(movie550, movie550, movie550))

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onToggleGridPage() = Unit
    }
    val navController = rememberNavController()
    MaterialTheme {
        MoviesListPage(title, listState, navController)
    }
}