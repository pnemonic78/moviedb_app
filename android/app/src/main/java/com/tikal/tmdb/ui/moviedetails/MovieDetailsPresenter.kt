package com.tikal.tmdb.ui.moviedetails

import android.util.Log
import com.tikal.tmdb.data.source.TmdbDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ronelg on 12/19/17.
 */
class MovieDetailsPresenter(
    private val repository: TmdbDataSource,
    private val view: MovieDetailsContract.View
) : MovieDetailsContract.Presenter {

    private val TAG = "MovieDetailsPresenter"

    init {
        view.presenter = this
    }

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun subscribe() {
    }

    override fun unsubscribe() {
        disposables.clear()
    }

    override fun loadMovie(movieId: Long) {
        view.showLoadingIndicator(true)

        val disposable = repository.getMovieDetails(movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ res ->
                view.showMovie(res)
                view.showLoadingIndicator(false)
            }, { t ->
                Log.e(TAG, "loadMovie($movieId) error: $t")
                view.showLoadingIndicator(false)
            })

        disposables.add(disposable)
    }

}