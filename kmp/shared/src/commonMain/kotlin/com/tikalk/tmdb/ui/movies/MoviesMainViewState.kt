package com.tikalk.tmdb.ui.movies

import com.tikalk.tmdb.ViewState

interface MoviesMainViewState : ViewState {
    val moviesNowPlayingViewState: MoviesCarouselViewState
    val moviesPopularViewState: MoviesCarouselViewState
    val moviesTopRatedViewState: MoviesCarouselViewState
    val moviesUpcomingViewState: MoviesCarouselViewState
}