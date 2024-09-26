package com.tikalk.tmdb.ui.movies

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
import com.tikalk.tmdb.now.MoviesNowPlayingSource
import com.tikalk.tmdb.popular.MoviesPopularSource
import com.tikalk.tmdb.top_rated.MoviesTopRatedSource
import com.tikalk.tmdb.upcoming.MoviesUpcomingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(private val repository: TmdbDataSource) : BaseViewModel() {

    private val _uiState = MutableStateFlow(MoviesMainViewState())
    val uiState = _uiState.asStateFlow()

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

    init {
        _uiState.update { currentState ->
            currentState.copy(
                moviesNowPlaying = moviesNowPlaying,
                moviesPopular = moviesPopular,
                moviesTopRated = moviesTopRated,
                moviesUpcoming = moviesUpcoming
            )
        }
    }

    fun onMovieClicked(movie: MovieEntity, navController: NavController) {
        navController.navigate("${MoviesScreen.Details.route}/${movie.id}")
    }

    fun onTitleClicked(type: MoviesPageType, navController: NavHostController) {
        when (type) {
            MoviesPageType.NOW_PLAYING -> navigateMoviesNowPlaying(navController)
            MoviesPageType.POPULAR -> navigateMoviesPopular(navController)
            MoviesPageType.TOP_RATED -> navigateMoviesTopRated(navController)
            MoviesPageType.UPCOMING -> navigateMoviesUpcoming(navController)
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
}