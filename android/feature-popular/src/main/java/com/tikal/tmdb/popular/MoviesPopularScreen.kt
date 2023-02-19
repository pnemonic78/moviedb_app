package com.tikal.tmdb.popular

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikal.tmdb.movies.MoviesPageViewState
import com.tikal.tmdb.movies.MoviesScreen

@Composable
fun MoviesPopularScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(id = R.string.popular)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}