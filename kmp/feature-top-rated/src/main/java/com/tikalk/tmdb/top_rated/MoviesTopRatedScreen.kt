package com.tikalk.tmdb.top_rated

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewModel
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_top_rated.generated.resources.Res
import movie_db_kmp.feature_top_rated.generated.resources.top_rated
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesTopRatedScreen(
    viewModel: MoviesPageViewModel,
    navController: NavController
) {
    LaunchedEffect(viewModel) {
        viewModel.loadMovies(MoviesTopRatedSource::create)
    }

    MoviesScreen(
        title = stringResource(Res.string.top_rated),
        viewModel = viewModel,
        navController = navController
    )
}