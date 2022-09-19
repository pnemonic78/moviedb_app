package com.tikal.tmdb.moviedetails

import androidx.navigation.NavController
import com.tikal.tmdb.UiState
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailsUiState : UiState {
    fun movieDetails(movieId: Long): StateFlow<MovieEntity?>

    fun onPosterClicked(movie: MovieEntity, navController: NavController)
}