package com.tikalk.tmdb.moviedetails

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.BackButton
import com.tikalk.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterScreen(
    navController: NavController,
    posterPathSuffix: String?
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {
                BackButton(navController = navController)
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Color.Transparent
            ),
        )
    }) { innerPadding ->
        PosterPage(modifier = Modifier.padding(innerPadding), posterPathSuffix = posterPathSuffix)
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