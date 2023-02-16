package com.tikal.tmdb.ui.movies

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tikal.tmdb.BaseViewModel
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.model.MoviesPageType
import com.tikal.tmdb.data.source.TmdbDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(repository: TmdbDataSource) :
    BaseViewModel(repository),
    MainViewState {

    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    override val movies: StateFlow<List<MovieEntity>> = _movies

    private val _moviesNowPlaying = MutableStateFlow<List<MoviesPage>>(emptyList())

    private val _isGridPage = MutableStateFlow(false)
    override val isGridPage: StateFlow<Boolean> = _isGridPage

    private var loadMoviesJob: Job? = null
    private var loadMovieJob: Job? = null

    private val _launchUri = MutableLiveData<Uri>(null)
    val launchUri: LiveData<Uri> = _launchUri

    override fun onCleared() {
        super.onCleared()
        loadMoviesJob?.cancel()
        loadMoviesJob = null
        loadMovieJob?.cancel()
        loadMovieJob = null
    }

    fun loadMovies() {
        loadMoviesJob?.cancel()
        loadMoviesJob = viewModelScope.launch {
            showLoadingIndicator(true)

            try {
                repository.getMoviesNowPlaying()
                    .collect { pages ->
                        _movies.emit(pages.flatMap { it.movies })
                        _moviesNowPlaying.emit(pages)
                        showLoadingIndicator(false)
                    }
            } catch (e: Exception) {
                Timber.e(e, "loadMovies error: $e")
                showLoadingIndicator(false)
            }
        }
    }

    override fun onMovieClicked(movie: MovieEntity, navController: NavController) {
        showMovieDetails(movie, navController)
    }

    private fun showMovieDetails(movie: MovieEntity, navController: NavController) {
        navController.navigate("${MoviesScreen.Details.route}/${movie.id}")
    }

    private val _movieDetails = MutableStateFlow<MovieEntity?>(null)

    override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> {
        val movie = _movieDetails.value
        if (movie?.id == movieId) return _movieDetails

        loadMovieJob?.cancel()
        loadMovieJob = viewModelScope.launch {
            showLoadingIndicator(true)

            try {
                repository.getMovie(movieId)
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

    override fun onPosterClicked(movie: MovieEntity, navController: NavController) {
        navigateMoviePoster(movie, navController)
    }

    override fun onLinkClicked(movie: MovieEntity, uri: Uri) {
        navigateMovieLink(movie, uri)
    }

    private fun navigateMoviePoster(movie: MovieEntity, navController: NavController) {
        navController.navigate("${MoviesScreen.Poster.route}/${movie.id}")
    }

    private fun navigateMovieLink(movie: MovieEntity, uri: Uri) {
        Timber.v("link $movie")
        _launchUri.postValue(uri)
    }

    override fun onToggleGridPage() {
        viewModelScope.launch {
            _isGridPage.emit(_isGridPage.value.not())
        }
    }

    override fun onTitleClicked(type: MoviesPageType, navController: NavHostController) {
        when (type) {
            MoviesPageType.NOW_PLAYING -> navigateNowPlaying(navController)
            else -> TODO("show $type page")
        }
    }

    private fun navigateNowPlaying(navController: NavController) {
        navController.navigate(MoviesScreen.NowPlaying.route)
    }

    override val nowPlayingViewState = object : MoviesCarouselViewState {
        override val movies: StateFlow<List<MoviesPage>> = _moviesNowPlaying

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) {
            this@MainViewModel.onMovieClicked(movie, navController)
        }
    }
}