package com.tikalk.tmdb.ui

import com.tikal.Result
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.source.TmdbDataSource
import com.tikal.tmdb.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MoviesViewModel(private val dataSource: TmdbDataSource) : ViewModel() {

    private var page = 1
    private val _uiState = MutableStateFlow(UiState<List<MovieEntity>>())
    val uiState = _uiState.asStateFlow()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = dataSource.getMoviesPopular(page)!!
            _uiState.update {
                it.copy(state = Result.Success(movies.results))
            }
        }
    }

}