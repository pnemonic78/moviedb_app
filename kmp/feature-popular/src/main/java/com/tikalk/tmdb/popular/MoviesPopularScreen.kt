package com.tikalk.tmdb.popular

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_popular.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesPopularScreen(viewState: MoviesPageViewState, navController: NavController) {
    val title = stringResource(Res.string.popular)

    MoviesScreen(title = title, viewState = viewState, navController = navController)
}