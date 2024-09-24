package com.tikalk.tmdb.now

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_now_playing.generated.resources.Res
import movie_db_kmp.feature_now_playing.generated.resources.now_playing
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesNowPlayingScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(Res.string.now_playing)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}