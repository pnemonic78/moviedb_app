package com.tikalk.tmdb.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewModel
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_popular.generated.resources.Res
import movie_db_kmp.feature_popular.generated.resources.popular
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesPopularScreen(
    viewModel: MoviesPageViewModel,
    navController: NavController
) {
    LaunchedEffect(viewModel) {
        viewModel.loadMovies(MoviesPopularSource::create)
    }

    MoviesScreen(
        title = stringResource(Res.string.popular),
        viewModel = viewModel,
        navController = navController
    )
}