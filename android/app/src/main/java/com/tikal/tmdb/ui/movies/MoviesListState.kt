package com.tikal.tmdb.ui.movies

import androidx.navigation.NavController
import com.tikal.tmdb.UiState
import com.tikal.tmdb.model.Movie
import kotlinx.coroutines.flow.StateFlow

interface MoviesListState : UiState {
    val movies: StateFlow<List<Movie>?>

    fun onMovieClicked(movie: Movie, navController: NavController)
}