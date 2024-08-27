package com.tikalk.tmdb.movies

import androidx.navigation.NavController
import androidx.paging.PagingData
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MoviesPageViewState : ViewState {
    val isGridPage: StateFlow<Boolean>
    val movies: Flow<PagingData<MovieEntity>>

    fun onMovieClicked(movie: MovieEntity, navController: NavController)
    fun onToggleLayout()
}