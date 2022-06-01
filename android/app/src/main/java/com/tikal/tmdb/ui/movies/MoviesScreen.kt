package com.tikal.tmdb.ui.movies

sealed class MoviesScreen(val route: String) {
    object NowPlaying : MoviesScreen("now_playing")
    object Details : MoviesScreen("details")
}
