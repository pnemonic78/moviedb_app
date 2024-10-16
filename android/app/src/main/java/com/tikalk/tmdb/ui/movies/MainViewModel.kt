package com.tikalk.tmdb.ui.movies

import android.net.Uri
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tikalk.tmdb.BaseViewModel
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPageType
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.moviedetails.MovieDetailsViewState
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.now.MoviesNowPlayingSource
import com.tikalk.tmdb.popular.MoviesPopularSource
import com.tikalk.tmdb.top_rated.MoviesTopRatedSource
import com.tikalk.tmdb.upcoming.MoviesUpcomingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(repository: TmdbDataSource) : BaseViewModel(repository),
    MainViewState {

    private val moviesNowPlaying: Flow<PagingData<MovieEntity>> =
        Pager(PagingConfig(pageSize = 20)) {
            MoviesNowPlayingSource(repository)
        }.flow.cachedIn(viewModelScope)

    private val moviesPopular: Flow<PagingData<MovieEntity>> =
        Pager(PagingConfig(pageSize = 20)) {
            MoviesPopularSource(repository)
        }.flow.cachedIn(viewModelScope)

    private val moviesTopRated: Flow<PagingData<MovieEntity>> =
        Pager(PagingConfig(pageSize = 20)) {
            MoviesTopRatedSource(repository)
        }.flow.cachedIn(viewModelScope)

    private val moviesUpcoming: Flow<PagingData<MovieEntity>> =
        Pager(PagingConfig(pageSize = 20)) {
            MoviesUpcomingSource(repository)
        }.flow.cachedIn(viewModelScope)

    private val _isGridPage = MutableStateFlow(true)
    val isGridPage: StateFlow<Boolean> = _isGridPage

    private val _launchUri = MutableStateFlow<Uri?>(null)
    val launchUri: StateFlow<Uri?> = _launchUri

    fun onMovieClicked(movie: MovieEntity, navController: NavController) {
        showMovieDetails(movie, navController)
    }

    private fun showMovieDetails(movie: MovieEntity, navController: NavController) {
        navController.navigate("${MoviesScreen.Details.route}/${movie.id}")
    }

    private val _movieDetails = MutableStateFlow<MovieEntity?>(null)

    fun movieDetails(movieId: Long): StateFlow<MovieEntity?> {
        val movieValue = _movieDetails.value
        if (movieValue?.id == movieId) return _movieDetails

        viewModelScope.launch {
            showLoadingIndicator(true)

            try {
                val movie = repository.getMovie(movieId)
                _movieDetails.emit(movie)
                showLoadingIndicator(false)
            } catch (e: Exception) {
                Timber.e(e, "movieDetails($movieId) error: $e")
                _movieDetails.emit(null)
                showLoadingIndicator(false)
            }
        }
        return _movieDetails
    }

    fun onPosterClicked(movie: MovieEntity, navController: NavController) {
        navigateMoviePoster(movie, navController)
    }

    fun onLinkClicked(movie: MovieEntity, uri: Uri, handler: UriHandler) {
        navigateMovieLink(movie, uri, handler)
    }

    private fun navigateMoviePoster(movie: MovieEntity, navController: NavController) {
        navController.navigate("${MoviesScreen.Poster.route}/${movie.id}")
    }

    private fun navigateMovieLink(movie: MovieEntity, uri: Uri, handler: UriHandler? = null) {
        Timber.v("link $movie")
        if (handler != null ) {
            handler.openUri(uri.toString())
        } else {
            viewModelScope.launch {
                _launchUri.emit(uri)
            }
        }
    }

    fun onToggleGridPage() {
        viewModelScope.launch {
            _isGridPage.emit(_isGridPage.value.not())
        }
    }

    fun onTitleClicked(type: MoviesPageType, navController: NavHostController) {
        when (type) {
            MoviesPageType.now_playing -> navigateMoviesNowPlaying(navController)
            MoviesPageType.popular -> navigateMoviesPopular(navController)
            MoviesPageType.top_rated -> navigateMoviesTopRated(navController)
            MoviesPageType.upcoming -> navigateMoviesUpcoming(navController)
            else -> TODO("show $type page")
        }
    }

    private fun navigateMoviesNowPlaying(navController: NavController) {
        navController.navigate(MoviesScreen.NowPlaying.route)
    }

    private fun navigateMoviesPopular(navController: NavController) {
        navController.navigate(MoviesScreen.Popular.route)
    }

    private fun navigateMoviesTopRated(navController: NavController) {
        navController.navigate(MoviesScreen.TopRated.route)
    }

    private fun navigateMoviesUpcoming(navController: NavController) {
        navController.navigate(MoviesScreen.Upcoming.route)
    }

    override val moviesMainViewState = object : MoviesMainViewState {
        override val moviesNowPlayingViewState = object : MoviesCarouselViewState {
            override val movies: Flow<PagingData<MovieEntity>> = moviesNowPlaying

            override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                this@MainViewModel.onMovieClicked(movie, navController)

            override fun onTitleClicked(navController: NavHostController) =
                this@MainViewModel.onTitleClicked(MoviesPageType.now_playing, navController)

            override val pageViewState = object : MoviesPageViewState {
                override val isGridPage: StateFlow<Boolean> = this@MainViewModel.isGridPage
                override val movies: Flow<PagingData<MovieEntity>> = moviesNowPlaying

                override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                    this@MainViewModel.onMovieClicked(movie, navController)

                override fun onToggleLayout() = onToggleGridPage()

                override val isLoading: StateFlow<Boolean> = this@MainViewModel.isLoading
            }
        }

        override val moviesPopularViewState = object : MoviesCarouselViewState {
            override val movies: Flow<PagingData<MovieEntity>> = moviesPopular

            override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                this@MainViewModel.onMovieClicked(movie, navController)

            override fun onTitleClicked(navController: NavHostController) =
                this@MainViewModel.onTitleClicked(MoviesPageType.popular, navController)

            override val pageViewState = object : MoviesPageViewState {
                override val isGridPage: StateFlow<Boolean> = this@MainViewModel.isGridPage
                override val movies: Flow<PagingData<MovieEntity>> = moviesPopular

                override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                    this@MainViewModel.onMovieClicked(movie, navController)

                override fun onToggleLayout() = onToggleGridPage()

                override val isLoading: StateFlow<Boolean> = this@MainViewModel.isLoading
            }
        }

        override val moviesTopRatedViewState = object : MoviesCarouselViewState {
            override val movies: Flow<PagingData<MovieEntity>> = moviesTopRated

            override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                this@MainViewModel.onMovieClicked(movie, navController)

            override fun onTitleClicked(navController: NavHostController) =
                this@MainViewModel.onTitleClicked(MoviesPageType.top_rated, navController)

            override val pageViewState = object : MoviesPageViewState {
                override val isGridPage: StateFlow<Boolean> = this@MainViewModel.isGridPage
                override val movies: Flow<PagingData<MovieEntity>> = moviesTopRated

                override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                    this@MainViewModel.onMovieClicked(movie, navController)

                override fun onToggleLayout() = onToggleGridPage()

                override val isLoading: StateFlow<Boolean> = this@MainViewModel.isLoading
            }
        }

        override val moviesUpcomingViewState = object : MoviesCarouselViewState {
            override val movies: Flow<PagingData<MovieEntity>> = moviesUpcoming

            override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                this@MainViewModel.onMovieClicked(movie, navController)

            override fun onTitleClicked(navController: NavHostController) =
                this@MainViewModel.onTitleClicked(MoviesPageType.upcoming, navController)

            override val pageViewState = object : MoviesPageViewState {
                override val isGridPage: StateFlow<Boolean> = this@MainViewModel.isGridPage
                override val movies: Flow<PagingData<MovieEntity>> = moviesUpcoming

                override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
                    this@MainViewModel.onMovieClicked(movie, navController)

                override fun onToggleLayout() = onToggleGridPage()

                override val isLoading: StateFlow<Boolean> = this@MainViewModel.isLoading
            }
        }

        override val isLoading: StateFlow<Boolean> = this@MainViewModel.isLoading
    }

    override val movieDetailsViewState = object : MovieDetailsViewState {
        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            this@MainViewModel.movieDetails(movieId)

        override fun onPosterClicked(movie: MovieEntity, navController: NavController) =
            this@MainViewModel.onPosterClicked(movie, navController)

        override fun onLinkClicked(movie: MovieEntity, uri: Uri, handler: UriHandler) =
            this@MainViewModel.onLinkClicked(movie, uri, handler)

        override val isLoading: StateFlow<Boolean> = this@MainViewModel.isLoading
    }
}