package com.tikal.tmdb.ui.movies

import androidx.navigation.NavHostController
import com.tikal.tmdb.ViewState
import com.tikal.tmdb.data.model.MoviesPageType

interface MoviesMainViewState : ViewState {
    val moviesNowPlayingViewState: MoviesCarouselViewState
    val moviesPopularViewState: MoviesCarouselViewState
    val moviesTopRatedViewState: MoviesCarouselViewState
    val moviesUpcomingViewState: MoviesCarouselViewState
}