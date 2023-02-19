package com.tikal.tmdb.now

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikal.tmdb.movies.MoviesPageViewState
import com.tikal.tmdb.movies.MoviesScreen
import com.tikal.tmdb.now_playing.R

@Composable
fun MoviesNowPlayingScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(id = R.string.now_playing)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}