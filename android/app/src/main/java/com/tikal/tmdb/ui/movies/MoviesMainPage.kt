package com.tikal.tmdb.ui.movies

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tikal.tmdb.UiState
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import com.tikal.tmdb.moviedetails.MovieDetailsPage
import com.tikal.tmdb.moviedetails.MovieDetailsUiState
import com.tikal.tmdb.moviedetails.movie550Details
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesMainPage(
    mainState: UiState,
    listState: MoviesListState,
    detailsState: MovieDetailsUiState
) {
    val navController = rememberNavController()
    val title = mainState.title.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title.value) })
        }
    ) {
        NavHost(navController, startDestination = MoviesScreen.NowPlaying.route) {
            composable(
                "${MoviesScreen.Details.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) {
                MovieDetailsPage(
                    mainState,
                    detailsState,
                    navController,
                    it.arguments?.getLong("id") ?: 0L
                )
            }
            composable(MoviesScreen.NowPlaying.route) {
                MoviesListPage(mainState, listState, navController)
            }
        }
    }
}

@Preview
@Composable
private fun MoviesMainPagePreview() {
    val mainState = object : UiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Main")
    }
    val listState = object : MoviesListState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("")
        override val movies: StateFlow<List<Movie>?> = MutableStateFlow(listOf(movie550, movie550))
        override fun onMovieClicked(movie: Movie, navController: NavController) = Unit
    }
    val detailsState = object : MovieDetailsUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("")
        override fun movieDetails(movieId: Long): StateFlow<MovieDetails?> =
            MutableStateFlow(movie550Details)
    }
    MaterialTheme {
        MoviesMainPage(mainState, listState, detailsState)
    }
}