package com.tikal.tmdb.moviedetails

import androidx.lifecycle.ViewModel
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.MovieDetails
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
class MovieDetailsViewModel @Inject constructor(private val repository: TmdbDataSource) :
    ViewModel(),
    MovieDetailsUiState {

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean> = _isLoading

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)

    private var loadMovieJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        loadMovieJob?.cancel()
        loadMovieJob = null
    }

    override fun movieDetails(movieId: Long): StateFlow<MovieDetails?> {
        val movie = _movieDetails.value
        if (movie?.id == movieId) return _movieDetails

        loadMovieJob?.cancel()
        loadMovieJob = CoroutineScope(Dispatchers.Main).launch {
            showLoadingIndicator(true)

            try {
                repository.getMovieDetails(movieId)
                    .flowOn(Dispatchers.IO)
                    .collect { movie ->
                        _movieDetails.emit(movie)
                        showLoadingIndicator(false)
                    }
            } catch (e: Exception) {
                Timber.e(e, "movieDetails($movieId) error: $e")
                _movieDetails.emit(null)
                showLoadingIndicator(false)
            }
        }
        return _movieDetails
    }

    private suspend fun showLoadingIndicator(active: Boolean) {
        _isLoading.emit(active)
    }
}