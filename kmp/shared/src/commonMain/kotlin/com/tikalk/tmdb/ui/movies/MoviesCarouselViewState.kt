package com.tikalk.tmdb.ui.movies

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.movies.MoviesPageViewState
import kotlinx.coroutines.flow.Flow

interface MoviesCarouselViewState {
    val movies: Flow<PagingData<MovieEntity>>
    val pageViewState: MoviesPageViewState

    fun onMovieClicked(movie: MovieEntity, navController: NavController)
    fun onTitleClicked(navController: NavHostController)
}
