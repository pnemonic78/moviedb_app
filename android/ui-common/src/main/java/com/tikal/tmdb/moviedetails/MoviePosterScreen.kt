package com.tikal.tmdb.moviedetails

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.compose.AppTheme
import com.tikal.tmdb.compose.SimpleErrorScreen
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.ui.common.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviePosterScreen(
    viewState: MovieDetailsViewState,
    navController: NavController,
    movieId: Long
) {
    val title = stringResource(id = R.string.movie_poster)
    val movieState by viewState.movieDetails(movieId).collectAsState()

    if (movieState != null) {
        val movie = movieState!!

        PosterScreen(
            title = title,
            navController = navController,
            posterPathSuffix = movie.posterPath
        )
    } else {
        SimpleErrorScreen(
            title = title,
            navController = navController
        )
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val viewState = object : MovieDetailsViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            MutableStateFlow(movie550Details)

        override fun onPosterClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onLinkClicked(movie: MovieEntity, uri: Uri) = Unit
    }
    val navController = rememberNavController()
    AppTheme {
        MoviePosterScreen(
            viewState = viewState,
            navController = navController,
            movieId = movie550Details.id
        )
    }
}