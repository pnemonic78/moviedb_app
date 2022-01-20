package com.tikal.tmdb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: TmdbDataSource) : ViewModel(),
    MoviesUiState {

    private val _isLoading = MutableLiveData<Boolean>(false)
    override val isLoading: LiveData<Boolean> = _isLoading

    private val _movies = MutableLiveData<List<Movie>?>()
    override val movies: LiveData<List<Movie>?> = _movies

    private val _movieDetails = MutableLiveData<Movie?>()
    override val movieDetails: LiveData<Movie?> = _movieDetails

    private var loadMoviesJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        loadMoviesJob?.cancel()
        loadMoviesJob = null
    }

    fun loadMovies(forceUpdate: Boolean) {
        showLoadingIndicator(true)

        loadMoviesJob?.cancel()
        loadMoviesJob = CoroutineScope(Dispatchers.Main).launch {
            try {
                repository.getMoviesNowPlaying()
                    .flowOn(Dispatchers.IO)
                    .collect { movies ->
                        _movies.postValue(movies)
                        showLoadingIndicator(false)
                    }
            } catch (e: Exception) {
                Timber.e(e, "loadMovies error: $e")
                showLoadingIndicator(false)
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        showMovieDetails(movie)
    }

    private fun showLoadingIndicator(active: Boolean) {
        _isLoading.postValue(active)
    }

    private fun showMovieDetails(movie: Movie) {
        _movieDetails.postValue(movie)
    }

    fun onMovieDetailsShown(movie: Movie) {
        _movieDetails.value = null
    }
}