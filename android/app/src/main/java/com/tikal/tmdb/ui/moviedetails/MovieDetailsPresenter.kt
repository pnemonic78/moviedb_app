package com.tikal.tmdb.ui.moviedetails

import com.tikal.tmdb.data.source.TmdbDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailsPresenter(
    private val repository: TmdbDataSource,
    private val view: MovieDetailsContract.View
) : MovieDetailsContract.Presenter {

    init {
        view.presenter = this
    }

    private var loadMovieJob: Job? = null

    override fun subscribe() = Unit

    override fun unsubscribe() {
        loadMovieJob?.cancel()
        loadMovieJob = null
    }

    override fun loadMovie(movieId: Long) {
        view.showLoadingIndicator(true)

        loadMovieJob?.cancel()
        loadMovieJob = CoroutineScope(Dispatchers.Main).launch {
            try {
                repository.getMovieDetails(movieId)
                    .flowOn(Dispatchers.IO)
                    .collect { res ->
                        view.showMovie(res)
                        view.showLoadingIndicator(false)
                    }
            } catch (e: Exception) {
                Timber.e(e, "loadMovie($movieId) error: $e")
                view.showLoadingIndicator(false)
            }
        }
    }
}