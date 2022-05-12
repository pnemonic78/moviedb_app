package com.tikal.tmdb.ui.movies

import com.tikal.tmdb.model.Movie
import kotlinx.coroutines.flow.StateFlow

interface MoviesUiState {
    val isLoading: StateFlow<Boolean>
    val movieDetails: StateFlow<Movie?>
    val movies: StateFlow<List<Movie>?>

    fun onMovieClicked(movie: Movie)
}