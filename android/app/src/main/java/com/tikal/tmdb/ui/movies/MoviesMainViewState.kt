package com.tikal.tmdb.ui.movies

import androidx.navigation.NavHostController
import com.tikal.tmdb.ViewState
import com.tikal.tmdb.data.model.MoviesPageType

interface MoviesMainViewState : ViewState {
    fun onTitleClicked(type: MoviesPageType, navController: NavHostController)

    val nowPlayingViewState: MoviesCarouselViewState
}