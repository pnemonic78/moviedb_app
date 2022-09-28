package com.tikal.tmdb.movies

import androidx.navigation.NavController
import com.tikal.tmdb.UiState
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.StateFlow

interface MoviesPageState : UiState {
    val movies: StateFlow<List<MovieEntity>>

    fun onMovieClicked(movie: MovieEntity, navController: NavController)
}