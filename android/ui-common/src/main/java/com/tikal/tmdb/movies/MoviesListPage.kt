package com.tikal.tmdb.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.MovieListTile
import com.tikal.tmdb.compose.AppTheme
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.page550
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesListPage(
    modifier: Modifier = Modifier,
    viewState: MoviesPageViewState,
    navController: NavController
) {
    val pages by viewState.pages.collectAsState()
    val movies = pages.flatMap { it.movies }
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = movies.size) { index ->
            val movie = movies[index]
            MovieListTile(
                movie = movie,
                onMovieClicked = { viewState.onMovieClicked(movie, navController) }
            )
        }
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val viewState = object : MoviesPageViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(false)
        override val pages: StateFlow<List<MoviesPage>> = MutableStateFlow(listOf(page550))

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onToggleLayout() = Unit
    }
    val navController = rememberNavController()
    AppTheme {
        MoviesListPage(viewState = viewState, navController = navController)
    }
}