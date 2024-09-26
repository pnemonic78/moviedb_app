package com.tikalk.tmdb.moviedetails

import android.net.Uri
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tikalk.tmdb.BaseViewModel
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.source.TmdbDataSource
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: TmdbDataSource,
    private val posterScreenRoute: String
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailsViewState())
    val uiState = _uiState.asStateFlow()

    private fun showLoadingIndicator(active: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isLoading = active)
        }
    }

    fun fetchMovie(movieId: Long) {
        val movieValue = _uiState.value.movie
        if (movieValue?.id == movieId) return

        showLoadingIndicator(true)

        viewModelScope.launch {
            try {
                val movie = repository.getMovie(movieId)
                _uiState.update { currentState ->
                    currentState.copy(isLoading = false, movie = movie)
                }
            } catch (e: Exception) {
                Napier.e("movieDetails($movieId) error: $e", e)
                _uiState.update { currentState ->
                    currentState.copy(isLoading = false, movie = null)
                }
            }
        }
    }

    fun onPosterClicked(movie: MovieEntity, navController: NavController) {
        navigateMoviePoster(movie, navController)
    }

    fun onLinkClicked(movie: MovieEntity, uri: Uri, handler: UriHandler?) {
        navigateMovieLink(movie, uri, handler)
    }

    private fun navigateMoviePoster(movie: MovieEntity, navController: NavController) {
        navController.navigate("${posterScreenRoute}/${movie.id}")
    }

    private fun navigateMovieLink(movie: MovieEntity, uri: Uri, handler: UriHandler? = null) {
        Napier.v("link $movie")
        handler?.openUri(uri.toString())
    }

}