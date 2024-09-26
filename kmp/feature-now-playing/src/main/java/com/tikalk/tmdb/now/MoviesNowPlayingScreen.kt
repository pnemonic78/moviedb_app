package com.tikalk.tmdb.now

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.tikalk.tmdb.movies.MoviesPageViewModel
import com.tikalk.tmdb.movies.MoviesScreen
import movie_db_kmp.feature_now_playing.generated.resources.Res
import movie_db_kmp.feature_now_playing.generated.resources.now_playing
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesNowPlayingScreen(
    viewModel: MoviesPageViewModel,
    navController: NavController
) {
    LaunchedEffect(viewModel) {
        viewModel.loadMovies(MoviesNowPlayingSource::create)
    }

    MoviesScreen(
        title = stringResource(Res.string.now_playing),
        viewModel = viewModel,
        navController = navController
    )
}