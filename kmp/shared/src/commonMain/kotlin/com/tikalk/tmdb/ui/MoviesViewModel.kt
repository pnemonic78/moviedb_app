package com.tikalk.tmdb.ui

import com.tikalk.Result
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.source.TmdbDataSource
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
                it.copy(state = Result.Success(movies.page.results))
            }
        }
    }

}