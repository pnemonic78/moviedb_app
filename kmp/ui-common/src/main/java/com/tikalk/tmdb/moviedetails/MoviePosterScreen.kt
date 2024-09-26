package com.tikalk.tmdb.moviedetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.tikalk.tmdb.compose.SimpleErrorScreen
import movie_db_kmp.ui_common.generated.resources.Res
import movie_db_kmp.ui_common.generated.resources.movie_poster
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviePosterScreen(
    viewModel: MovieDetailsViewModel,
    navController: NavController
) {
    val viewState = viewModel.uiState.collectAsState()
    val movie = viewState.value.movie

    if (movie != null) {
        PosterScreen(
            navController = navController,
            posterPathSuffix = movie.posterPath
        )
    } else {
        val title = stringResource(Res.string.movie_poster)
        SimpleErrorScreen(
            title = title,
            navController = navController
        )
    }
}
