package com.tikal.tmdb.ui.movies

import com.tikal.tmdb.moviedetails.MovieDetailsViewState
import com.tikal.tmdb.movies.MoviesPageViewState

interface MainViewState {
    val moviesPageViewState: MoviesPageViewState
    val movieDetailsViewState: MovieDetailsViewState
    val moviesMainViewState: MoviesMainViewState
}