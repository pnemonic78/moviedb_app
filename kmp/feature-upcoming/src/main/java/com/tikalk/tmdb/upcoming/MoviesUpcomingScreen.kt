package com.tikalk.tmdb.upcoming

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_upcoming.generated.resources.Res
import movie_db_kmp.feature_upcoming.generated.resources.upcoming
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesUpcomingScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(Res.string.upcoming)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}