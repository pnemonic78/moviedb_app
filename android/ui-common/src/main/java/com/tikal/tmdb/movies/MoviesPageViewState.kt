package com.tikal.tmdb.movies

import androidx.navigation.NavController
import androidx.paging.PagingData
import com.tikal.tmdb.ViewState
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MoviesPageViewState : ViewState {
    val isGridPage: StateFlow<Boolean>
    val movies: Flow<PagingData<MovieEntity>>

    fun onMovieClicked(movie: MovieEntity, navController: NavController)
    fun onToggleLayout()
}