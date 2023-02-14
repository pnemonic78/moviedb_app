package com.tikal.tmdb.moviedetails

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.ui.common.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailsPage(
    viewState: MovieDetailsViewState,
    navController: NavController,
    movieId: Long
) {
    val movieState = viewState.movieDetails(movieId).collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val title = stringResource(id = R.string.movie_details)
    LaunchedEffect(coroutineScope) {
        viewState.title.emit(title)
    }

    val movie = movieState.value
    if (movie != null) {
        MovieDetailsContent(
            movie = movie,
            onPosterClick = { viewState.onPosterClicked(movie, navController) },
            onLinkClick = { viewState.onLinkClicked(movie, it) }
        )
    } else {
        Box(modifier = Modifier.background(color = MaterialTheme.colors.error))
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val viewState = object : MovieDetailsViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Movie Details")
        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            MutableStateFlow(movie550Details)

        override fun onPosterClicked(movie: MovieEntity, navController: NavController) {
            println("Poster clicked")
        }
        override fun onLinkClicked(movie: MovieEntity, uri: Uri) {
            println("Link clicked $uri")
        }
    }
    val navController = rememberNavController()
    MaterialTheme {
        MovieDetailsPage(viewState, navController, movie550Details.id)
    }
}