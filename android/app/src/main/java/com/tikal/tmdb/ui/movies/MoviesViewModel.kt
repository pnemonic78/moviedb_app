package com.tikal.tmdb.ui.movies

import androidx.lifecycle.ViewModel
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
class MoviesViewModel @Inject constructor(private val repository: TmdbDataSource) :
    ViewModel(),
    MoviesUiState {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    override val isLoading: StateFlow<Boolean> = _isLoading

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    override val movies: StateFlow<List<Movie>> = _movies

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    override val movieDetails: StateFlow<Movie?> = _movieDetails

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

    override fun onMovieClicked(movie: Movie) {
        CoroutineScope(Dispatchers.Main).launch {
            showMovieDetails(movie)
        }
    }

    private suspend fun showLoadingIndicator(active: Boolean) {
        _isLoading.emit(active)
    }

    private suspend fun showMovieDetails(movie: Movie) {
        _movieDetails.emit(movie)
    }

    fun onMovieDetailsShown(movie: Movie) {
        _movieDetails.value = null
    }
}