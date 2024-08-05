package com.tikalk.tmdb.ui.movies

sealed class MoviesScreen(val route: String) {
    object Details : MoviesScreen("details")
    object Main : MoviesScreen("main")
    object NowPlaying : MoviesScreen("now_playing")
    object Popular : MoviesScreen("popular")
    object Poster : MoviesScreen("poster")
    object TopRated : MoviesScreen("top_rated")
    object Upcoming : MoviesScreen("upcoming")
}
