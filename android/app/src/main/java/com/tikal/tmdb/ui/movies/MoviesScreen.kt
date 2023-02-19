package com.tikal.tmdb.ui.movies

sealed class MoviesScreen(val route: String) {
    object Main : MoviesScreen("main")
    object NowPlaying : MoviesScreen("now_playing")
    object Popular : MoviesScreen("popular")
    object TopRated : MoviesScreen("top_rated")
    object Upcoming : MoviesScreen("upcoming")
    object Details : MoviesScreen("details")
    object Poster : MoviesScreen("poster")
}
