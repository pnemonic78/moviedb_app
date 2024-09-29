package com.tikalk.tmdb.ui.movies

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import com.tikalk.tmdb.compose.OnClickCallback
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPageType
import com.tikalk.tmdb.movies.OnMovieClickCallback
import kotlinx.coroutines.flow.Flow
import movie_db_kmp.shared.generated.resources.Res
import movie_db_kmp.shared.generated.resources.now_playing
import movie_db_kmp.shared.generated.resources.popular
import movie_db_kmp.shared.generated.resources.top_rated
import movie_db_kmp.shared.generated.resources.upcoming
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesMainPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val viewState = viewModel.uiState.collectAsState()
    val onMovieClicked: OnMovieClickCallback = { viewModel.onMovieClicked(it, navController) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MoviesMainPageCategory(
            title = stringResource(Res.string.now_playing),
            movies = viewState.value.moviesNowPlaying,
            onTitleClicked = {
                viewModel.onTitleClicked(MoviesPageType.now_playing, navController)
            },
            onMovieClicked = onMovieClicked
        )
        MoviesMainPageCategory(
            title = stringResource(Res.string.popular),
            movies = viewState.value.moviesPopular,
            onTitleClicked = {
                viewModel.onTitleClicked(MoviesPageType.popular, navController)
            },
            onMovieClicked = onMovieClicked
        )
        MoviesMainPageCategory(
            title = stringResource(Res.string.top_rated),
            movies = viewState.value.moviesTopRated,
            onTitleClicked = {
                viewModel.onTitleClicked(MoviesPageType.top_rated, navController)
            },
            onMovieClicked = onMovieClicked
        )
        MoviesMainPageCategory(
            title = stringResource(Res.string.upcoming),
            movies = viewState.value.moviesUpcoming,
            onTitleClicked = {
                viewModel.onTitleClicked(MoviesPageType.upcoming, navController)
            },
            onMovieClicked = onMovieClicked
        )
    }
}

@Composable
private fun MoviesMainPageCategory(
    title: String,
    movies: Flow<PagingData<MovieEntity>>,
    onTitleClicked: OnClickCallback,
    onMovieClicked: OnMovieClickCallback
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTitleClicked() },
        text = title,
        style = MaterialTheme.typography.headlineSmall
    )
    MoviesCarousel(
        movies = movies,
        onMovieClicked = onMovieClicked
    )
}
