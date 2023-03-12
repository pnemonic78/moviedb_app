package com.tikal.tmdb.upcoming

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikal.tmdb.movies.MoviesPageViewState
import com.tikal.tmdb.movies.MoviesScreen

@Composable
fun MoviesUpcomingScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(id = R.string.upcoming)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}