package com.tikal.tmdb.ui.movies

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.movies.MoviesPageViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MoviesCarouselViewState {
    val movies: Flow<PagingData<MovieEntity>>

    fun onMovieClicked(movie: MovieEntity, navController: NavController)
    fun onTitleClicked(navController: NavHostController)

    val pageViewState: MoviesPageViewState
}
