package com.tikal.tmdb.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tikal.tmdb.movies.MoviesGridPage
import com.tikal.tmdb.movies.MoviesListPage
import com.tikal.tmdb.movies.MoviesPageViewState

@Composable
fun MoviesPopularPage(
    modifier: Modifier = Modifier,
    viewState: MoviesPageViewState,
    navController: NavController
) {
    val isGridPage = viewState.isGridPage.collectAsState()

    if (isGridPage.value) {
        MoviesGridPage(modifier = modifier, viewState = viewState, navController = navController)
    } else {
        MoviesListPage(modifier = modifier, viewState = viewState, navController = navController)
    }
}