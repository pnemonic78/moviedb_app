package com.tikalk.tmdb.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesGridPage
import com.tikalk.tmdb.movies.MoviesListPage
import com.tikalk.tmdb.movies.MoviesPageViewState

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