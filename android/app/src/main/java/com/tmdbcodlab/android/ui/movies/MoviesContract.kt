package com.tmdbcodlab.android.ui.movies

import com.tmdbcodlab.android.BasePresenter
import com.tmdbcodlab.android.BaseView
import com.tmdbcodlab.android.model.Movie

/**
 * Created by ronelg on 12/19/17.
 */
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