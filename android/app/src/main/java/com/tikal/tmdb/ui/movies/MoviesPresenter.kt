package com.tikal.tmdb.ui.movies

import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesPresenter(
    private val repository: TmdbDataSource,
    private val view: MoviesContract.View
) : MoviesContract.Presenter {

    init {
        view.presenter = this
    }

    private var loadMoviesJob: Job? = null

    override fun subscribe() {
        loadMovies(false)
    }

    override fun unsubscribe() {
        loadMoviesJob?.cancel()
        loadMoviesJob = null
    }

    override fun loadMovies(forceUpdate: Boolean) {
        view.showLoadingIndicator(true)

        loadMoviesJob?.cancel()
        loadMoviesJob = CoroutineScope(Dispatchers.Main).launch {
            try {
                repository.getMoviesNowPlaying()
                    .flowOn(Dispatchers.IO)
                    .collect { res ->
                        view.showMovies(res)
                        view.showLoadingIndicator(false)
                    }
            } catch (e: Exception) {
                Timber.e(e, "loadMovies error: $e")
                view.showLoadingIndicator(false)
            }
        }
    }

    override fun onMovieClicked(movie: Movie) {
        view.showMovieDetails(movie)
    }
}