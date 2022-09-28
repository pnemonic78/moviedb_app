package com.tikal.tmdb.now

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tikal.tmdb.movies.MoviesGridPage
import com.tikal.tmdb.movies.MoviesListPage
import com.tikal.tmdb.movies.MoviesPageState
import com.tikal.tmdb.now_playing.R

@Composable
fun MoviesNowPlayingPage(uiState: MoviesPageState, navController: NavController) {
    val title = stringResource(id = R.string.now_playing)
    val isGridPage = uiState.isGridPage.collectAsState()

    if (isGridPage.value) {
        MoviesGridPage(title, uiState, navController)
    } else {
        MoviesListPage(title, uiState, navController)
    }
}