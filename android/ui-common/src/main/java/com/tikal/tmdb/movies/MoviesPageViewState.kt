package com.tikal.tmdb.movies

import androidx.navigation.NavController
import com.tikal.tmdb.ViewState
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import kotlinx.coroutines.flow.StateFlow

interface MoviesPageViewState : ViewState {
    val isGridPage: StateFlow<Boolean>
    val pages: StateFlow<List<MoviesPage>>

    fun onMovieClicked(movie: MovieEntity, navController: NavController)
    fun onToggleLayout()
}