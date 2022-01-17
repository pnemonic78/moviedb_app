package com.tikal.tmdb.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.model.MovieDetails
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
class MovieDetailsViewModel @Inject constructor(private val repository: TmdbDataSource) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _movieDetails = MutableLiveData<MovieDetails?>()
    val movieDetails: LiveData<MovieDetails?> = _movieDetails

    private var loadMovieJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        loadMovieJob?.cancel()
        loadMovieJob = null
    }

     fun loadMovie(movieId: Long) {
        showLoadingIndicator(true)

        loadMovieJob?.cancel()
        loadMovieJob = CoroutineScope(Dispatchers.Main).launch {
            try {
                repository.getMovieDetails(movieId)
                    .flowOn(Dispatchers.IO)
                    .collect { movie ->
                        _movieDetails.postValue(movie)
                        showLoadingIndicator(false)
                    }
            } catch (e: Exception) {
                Timber.e(e, "loadMovie($movieId) error: $e")
                showLoadingIndicator(false)
            }
        }
    }

    private fun showLoadingIndicator(active: Boolean) {
        _isLoading.postValue(active)
    }
}