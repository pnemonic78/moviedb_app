package com.tikalk.tmdb.moviedetails

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavController
import com.tikalk.tmdb.compose.SimpleErrorContent
import com.tikalk.tmdb.compose.SimpleScreen
import movie_db_kmp.ui_common.generated.resources.Res
import movie_db_kmp.ui_common.generated.resources.movie_details
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    navController: NavController
) {
    val title = stringResource(Res.string.movie_details)

    SimpleScreen(
        title = title,
        navController = navController
    ) { innerPadding ->
        MovieDetailsPage(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
fun MovieDetailsPage(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel,
    navController: NavController
) {
    val viewState = viewModel.uiState.collectAsState()
    val movie = viewState.value.movie

    if (movie != null) {
        val uriHandler = LocalUriHandler.current

        MovieDetailsContent(
            modifier = modifier,
            movie = movie,
            onPosterClick = { viewModel.onPosterClicked(movie, navController) },
            onLinkClick = { viewModel.onLinkClicked(movie, it, uriHandler) }
        )
    } else {
        SimpleErrorContent(modifier = modifier)
    }
}
