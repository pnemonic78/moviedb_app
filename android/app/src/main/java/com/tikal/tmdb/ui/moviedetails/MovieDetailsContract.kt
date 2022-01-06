package com.tikal.tmdb.ui.moviedetails

import com.tikal.tmdb.BasePresenter
import com.tikal.tmdb.BaseView
import com.tikal.tmdb.model.MovieDetails

/**
 * Created by ronelg on 12/19/17.
 */
interface MovieDetailsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator(active: Boolean)

        fun showMovie(movie: MovieDetails)
    }

    interface Presenter : BasePresenter {

        fun loadMovie(movieId: Long)
    }
}