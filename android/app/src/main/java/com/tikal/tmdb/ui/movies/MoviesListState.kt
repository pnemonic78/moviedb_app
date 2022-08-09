package com.tikal.tmdb.ui.movies

import androidx.navigation.NavController
import com.tikal.tmdb.UiState
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.StateFlow

interface MoviesListState : UiState {
    val movies: StateFlow<List<MovieEntity>>

    fun onMovieClicked(movie: MovieEntity, navController: NavController)
}