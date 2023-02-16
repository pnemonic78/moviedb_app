package com.tikal.tmdb.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.R
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.model.MoviesPageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesMainPage(
    viewState: MoviesMainViewState,
    navController: NavHostController
) {
    val title = stringResource(id = R.string.title)
    LaunchedEffect(viewState) {
        viewState.title.emit(title)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { viewState.onTitleClicked(MoviesPageType.NOW_PLAYING, navController) },
            text = stringResource(id = R.string.now_playing),
            style = MaterialTheme.typography.h5
        )
        MoviesCarousel(
            viewState = viewState.nowPlayingViewState,
            navController = navController
        )
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val nowPlayingViewState = object : MoviesCarouselViewState {
        override val movies: StateFlow<List<MoviesPage>> = MutableStateFlow(emptyList())
        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
    }
    val viewState = object : MoviesMainViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Main")
        override val nowPlayingViewState: MoviesCarouselViewState = nowPlayingViewState
        override fun onTitleClicked(type: MoviesPageType, navController: NavHostController) = Unit
    }
    val navController = rememberNavController()

    MaterialTheme {
        MoviesMainPage(viewState, navController)
    }
}