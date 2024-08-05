package com.tikalk.tmdb.ui.movies

import com.tikalk.tmdb.moviedetails.MovieDetailsViewState

interface MainViewState {
    val moviesMainViewState: MoviesMainViewState
    val movieDetailsViewState: MovieDetailsViewState
}