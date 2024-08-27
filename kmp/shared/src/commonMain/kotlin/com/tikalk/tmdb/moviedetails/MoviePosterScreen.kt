package com.tikalk.tmdb.moviedetails

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.SimpleErrorScreen
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.movies.movie550Details
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import movie_db_kmp.shared.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviePosterScreen(
    viewState: MovieDetailsViewState,
    navController: NavController,
    movieId: Long
) {
    val movieState by viewState.movieDetails(movieId).collectAsState()

    if (movieState != null) {
        val movie = movieState!!

        PosterScreen(
            navController = navController,
            posterPathSuffix = movie.posterPath
        )
    } else {
        val title = "stringResource(Res.string.movie_poster)"
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