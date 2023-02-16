package com.tikal.tmdb.ui.movies

import androidx.navigation.NavController
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import kotlinx.coroutines.flow.StateFlow

interface MoviesCarouselViewState {
    val movies: StateFlow<List<MoviesPage>>
    fun onMovieClicked(movie: MovieEntity, navController: NavController)
}
