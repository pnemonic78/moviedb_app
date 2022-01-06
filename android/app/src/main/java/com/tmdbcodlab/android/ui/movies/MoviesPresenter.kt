package com.tmdbcodlab.android.ui.movies

import android.util.Log
import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ronelg on 12/19/17.
 */
class MoviesPresenter(
    private val repository: TmdbDataSource,
    private val view: MoviesContract.View
) : MoviesContract.Presenter {

    private val TAG = "MoviesPresenter"

    init {
        view.presenter = this
    }

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun subscribe() {
        loadMovies(false)
    }

    override fun unsubscribe() {
        disposables.clear()
    }

    override fun loadMovies(forceUpdate: Boolean) {
        view.showLoadingIndicator(true)

        val disposable = repository.getMoviesNowPlaying()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ res ->
                view.showMovies(res)
                view.showLoadingIndicator(false)
            }, { t ->
                Log.e(TAG, "loadMovies error: $t")
                view.showLoadingIndicator(false)
            })

        disposables.add(disposable)
    }

    override fun onMovieClicked(movie: Movie) {
        view.showMovieDetails(movie)
    }
}