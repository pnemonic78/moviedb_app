package com.tikal.tmdb.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.MovieGridTile
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.movie550
import com.tikal.tmdb.ui.common.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesGridPage(title: String, uiState: MoviesListState, navController: NavController) {
    val movies by uiState.movies.collectAsState()
    val scrollState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(coroutineScope) {
        uiState.title.emit(title)
    }

    LazyVerticalGrid(
        modifier = Modifier.padding(8.dp),
        state = scrollState,
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.posterWidth)),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            val movie = movies[index]
            MovieGridTile(movie, onMovieClicked = { uiState.onMovieClicked(movie, navController) })
        }
    }
}

@Preview(widthDp = 450)
@Composable
private fun ThisPreview() {
    val title = "Grid Page"
    val listState = object : MoviesListState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Movies Grid")
        override val movies: StateFlow<List<MovieEntity>> =
            MutableStateFlow(listOf(movie550, movie550, movie550, movie550))

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
    }
    val navController = rememberNavController()
    MaterialTheme {
        MoviesGridPage(title, listState, navController)
    }
}