package com.tikal.tmdb.ui.movies

import androidx.navigation.NavController
import com.tikal.tmdb.BaseViewModel
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(repository: TmdbDataSource) :
    BaseViewModel(repository),
    MoviesListState {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    override val movies: StateFlow<List<Movie>> = _movies

    private var loadMoviesJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        loadMoviesJob?.cancel()
        loadMoviesJob = null
    }

    fun loadMovies() {
        loadMoviesJob?.cancel()
        loadMoviesJob = CoroutineScope(Dispatchers.Main).launch {
            showLoadingIndicator(true)

            try {
                repository.getMoviesNowPlaying()
                    .flowOn(Dispatchers.IO)
                    .collect { movies ->
                        _movies.emit(movies)
                        showLoadingIndicator(false)
                    }
            } catch (e: Exception) {
                Timber.e(e, "loadMovies error: $e")
                showLoadingIndicator(false)
            }
        }
    }

    override fun onMovieClicked(movie: Movie, navController: NavController) {
        showMovieDetails(movie, navController)
    }

    private fun showMovieDetails(movie: Movie, navController: NavController) {
        navController.navigate("${MoviesScreen.Details.route}/${movie.id}")
    }
}