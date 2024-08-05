package com.tikalk.tmdb.ui.movies

import androidx.navigation.NavHostController
import com.tikalk.tmdb.ViewState
import com.tikalk.tmdb.data.model.MoviesPageType

interface MoviesMainViewState : ViewState {
    val moviesNowPlayingViewState: MoviesCarouselViewState
    val moviesPopularViewState: MoviesCarouselViewState
    val moviesTopRatedViewState: MoviesCarouselViewState
    val moviesUpcomingViewState: MoviesCarouselViewState
}