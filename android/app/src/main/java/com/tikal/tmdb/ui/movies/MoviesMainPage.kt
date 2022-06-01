package com.tikal.tmdb.ui.movies

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.MovieDetails
import com.tikal.tmdb.moviedetails.MovieDetailsPage
import com.tikal.tmdb.moviedetails.MovieDetailsUiState
import com.tikal.tmdb.moviedetails.movie550Details
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesMainPage(uiState: MoviesUiState, uiStateDetails: MovieDetailsUiState) {
    val navController = rememberNavController()

    Scaffold {
        NavHost(navController, startDestination = MoviesScreen.NowPlaying.route) {
            composable(MoviesScreen.Details.route) {
                MovieDetailsPage(uiStateDetails, navController)
            }
            composable(MoviesScreen.NowPlaying.route) {
                MoviesListPage(uiState, navController)
            }
        }
    }
}

@Preview
@Composable
private fun MoviesMainPagePreview() {
    val uiState = object : MoviesUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val movieDetails: StateFlow<Movie?> = MutableStateFlow(null)
        override val movies: StateFlow<List<Movie>?> = MutableStateFlow(listOf(movie550, movie550))
        override fun onMovieClicked(movie: Movie) = Unit
    }
    val uiStateDetails = object : MovieDetailsUiState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow<Boolean>(false)
        override val movieDetails: StateFlow<MovieDetails?> = MutableStateFlow(movie550Details)
    }
    MaterialTheme {
        MoviesMainPage(uiState, uiStateDetails)
    }
}