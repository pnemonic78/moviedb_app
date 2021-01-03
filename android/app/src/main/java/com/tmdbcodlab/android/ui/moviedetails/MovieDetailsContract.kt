package com.tmdbcodlab.android.ui.moviedetails

import com.tmdbcodlab.android.BasePresenter
import com.tmdbcodlab.android.BaseView
import com.tmdbcodlab.android.model.MovieDetails

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