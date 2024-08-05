package com.tikal.tmdb.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tikal.tmdb.R

@Composable
fun MoviesMainPage(
    modifier: Modifier = Modifier,
    viewState: MoviesMainViewState,
    navController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MoviesMainPageCategory(
            title = stringResource(id = com.tikal.tmdb.now_playing.R.string.now_playing),
            viewState = viewState.moviesNowPlayingViewState,
            navController = navController
        )
        MoviesMainPageCategory(
            title = stringResource(id = com.tikal.tmdb.popular.R.string.popular),
            viewState = viewState.moviesPopularViewState,
            navController = navController
        )
        MoviesMainPageCategory(
            title = stringResource(id = com.tikal.tmdb.top_rated.R.string.top_rated),
            viewState = viewState.moviesTopRatedViewState,
            navController = navController
        )
        MoviesMainPageCategory(
            title = stringResource(id = com.tikal.tmdb.upcoming.R.string.upcoming),
            viewState = viewState.moviesUpcomingViewState,
            navController = navController
        )
    }
}

@Composable
private fun MoviesMainPageCategory(
    title: String,
    viewState: MoviesCarouselViewState,
    navController: NavHostController
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { viewState.onTitleClicked(navController) },
        text = title,
        style = MaterialTheme.typography.headlineSmall
    )
    MoviesCarousel(
        viewState = viewState,
        navController = navController
    )
}
