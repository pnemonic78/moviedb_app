package com.tikalk.tmdb.upcoming

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.movies.MoviesScreen

@Composable
fun MoviesUpcomingScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(id = R.string.upcoming)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}