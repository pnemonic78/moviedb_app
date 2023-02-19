package com.tikal.tmdb.moviedetails

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.compose.AppTheme
import com.tikal.tmdb.compose.SimpleScreen
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PosterScreen(
    title: String,
    navController: NavController,
    posterPathSuffix: String?
) {
    SimpleScreen(
        title = title,
        navController = navController
    ) { innerPadding ->
        PosterPage(
            modifier = Modifier.padding(innerPadding),
            posterPathSuffix = posterPathSuffix
        )
    }
}

@Composable
fun PosterPage(
    modifier: Modifier = Modifier,
    posterPathSuffix: String?
) {
    PosterContent(
        modifier = modifier,
        posterPathSuffix = posterPathSuffix
    )
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