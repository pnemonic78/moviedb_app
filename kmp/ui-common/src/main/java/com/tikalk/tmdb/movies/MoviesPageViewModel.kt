package com.tikalk.tmdb.movies

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.tikalk.tmdb.BaseViewModel
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.source.TmdbDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MoviesPageViewModel(
    private val repository: TmdbDataSource,
    private val detailsScreenRoute: String
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(MoviesPageViewState())
    val uiState = _uiState.asStateFlow()

    fun onMovieClicked(movie: MovieEntity, navController: NavController) {
        navController.navigate("${detailsScreenRoute}/${movie.id}")
    }

    fun onToggleLayout() {
        _uiState.update { currentState ->
            currentState.copy(isGridPage = currentState.isGridPage.not())
        }
    }

    fun loadMovies(sourceFactory: (repository: TmdbDataSource) -> PagingSource<Int, MovieEntity>) {
        val movies: Flow<PagingData<MovieEntity>> = Pager(PagingConfig(pageSize = 20)) {
            sourceFactory(repository)
        }.flow.cachedIn(viewModelScope)

        _uiState.update { currentState ->
            currentState.copy(movies = movies)
        }
    }

}