package com.tikal.tmdb.ui.movies

import androidx.lifecycle.LiveData
import com.tikal.tmdb.model.Movie

interface MoviesUiState {
    val isLoading: LiveData<Boolean>
    val movieDetails: LiveData<Movie?>
    val movies: LiveData<List<Movie>?>

    fun onMovieClicked(movie: Movie)
}