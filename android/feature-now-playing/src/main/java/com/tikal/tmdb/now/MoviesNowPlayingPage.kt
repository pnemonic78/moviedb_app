package com.tikal.tmdb.now

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikal.tmdb.movies.MoviesListPage
import com.tikal.tmdb.movies.MoviesPageState
import com.tikal.tmdb.now_playing.R

@Composable
fun MoviesNowPlayingPage(uiState: MoviesPageState, navController: NavController) {
    val title = stringResource(id = R.string.now_playing)
    MoviesListPage(title, uiState, navController)
}