package com.tikal.tmdb.top_rated

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikal.tmdb.movies.MoviesPageViewState
import com.tikal.tmdb.movies.MoviesScreen

@Composable
fun MoviesTopRatedScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(id = R.string.top_rated)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}