package com.tikalk.tmdb.moviedetails

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.SimpleErrorContent
import com.tikalk.tmdb.compose.SimpleScreen
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ui.common.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailsScreen(
    viewState: MovieDetailsViewState,
    navController: NavController,
    movieId: Long
) {
    val title = stringResource(id = R.string.movie_details)

    SimpleScreen(
        title = title,
        navController = navController
    ) { innerPadding ->
        MovieDetailsPage(
            modifier = Modifier.padding(innerPadding),
            viewState = viewState,
            navController = navController,
            movieId = movieId
        )
    }
}

@Composable
fun MovieDetailsPage(
    modifier: Modifier = Modifier,
    viewState: MovieDetailsViewState,
    navController: NavController,
    movieId: Long
) {
    val movieState = viewState.movieDetails(movieId).collectAsState()
    val movie = movieState.value
    if (movie != null) {
        val uriHandler = LocalUriHandler.current

        MovieDetailsContent(
            modifier = modifier,
            movie = movie,
            onPosterClick = { viewState.onPosterClicked(movie, navController) },
            onLinkClick = { viewState.onLinkClicked(movie, it, uriHandler) }
        )
    } else {
        SimpleErrorContent(modifier = modifier)
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val viewState = object : MovieDetailsViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            MutableStateFlow(movie550Details)

        override fun onPosterClicked(movie: MovieEntity, navController: NavController) {
            println("Poster clicked")
        }

        override fun onLinkClicked(movie: MovieEntity, uri: Uri, handler: UriHandler) {
            println("Link clicked $uri")
        }
    }
    val navController = rememberNavController()
    AppTheme {
        MovieDetailsScreen(
            viewState = viewState,
            navController = navController,
            movieId = movie550Details.id
        )
    }
}