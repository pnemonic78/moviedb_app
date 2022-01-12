package com.tikal.tmdb.ui.movies

import com.tikal.tmdb.BasePresenter
import com.tikal.tmdb.BaseView
import com.tikal.tmdb.model.Movie

interface MoviesContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator(active: Boolean)

        fun showMovies(data: List<Movie>)

        fun showMovieDetails(movie: Movie)
    }

    interface Presenter : BasePresenter {

        fun loadMovies(forceUpdate: Boolean)

        fun onMovieClicked(movie: Movie)
    }
}