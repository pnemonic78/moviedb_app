package com.tikalk.tmdb.top_rated

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_top_rated.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesTopRatedScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(Res.string.top_rated)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}