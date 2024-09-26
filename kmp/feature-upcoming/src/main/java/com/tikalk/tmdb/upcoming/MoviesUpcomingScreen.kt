package com.tikalk.tmdb.upcoming

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewModel
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_upcoming.generated.resources.Res
import movie_db_kmp.feature_upcoming.generated.resources.upcoming
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesUpcomingScreen(
    viewModel: MoviesPageViewModel,
    navController: NavController
) {
    LaunchedEffect(viewModel) {
        viewModel.loadMovies(MoviesUpcomingSource::create)
    }

    MoviesScreen(
        title = stringResource(Res.string.upcoming),
        viewModel = viewModel,
        navController = navController
    )
}