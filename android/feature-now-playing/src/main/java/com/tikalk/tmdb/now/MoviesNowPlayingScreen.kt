package com.tikalk.tmdb.now

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.movies.MoviesScreen
import com.tikalk.tmdb.now_playing.R

@Composable
fun MoviesNowPlayingScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(id = R.string.now_playing)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}