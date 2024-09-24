package com.tikalk.tmdb

import androidx.navigation.NavController
import androidx.paging.PagingData
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.source.TmdbDataSource
import com.tikalk.tmdb.movies.MoviesPageViewState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MoviesViewModel(private val dataSource: TmdbDataSource) : ViewModel(), MoviesPageViewState {

    private var page = 1

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean> = _isLoading

    private val _isGridPage = MutableStateFlow(true)
    override val isGridPage: StateFlow<Boolean> = _isGridPage

    private val _movies = MutableStateFlow<PagingData<MovieEntity>>(PagingData.from(emptyList()))
    override val movies: Flow<PagingData<MovieEntity>> = _movies

    override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit

    override fun onToggleLayout() = onToggleGridPage()

    init {
        viewModelScope.launch { fetchMovies() }
    }

    private suspend fun fetchMovies() {
        _isLoading.emit(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = dataSource.getMoviesPopular(page)!!
                _isLoading.emit(false)
                _movies.update {
                    PagingData.from(movies.page.results)
                }
            } catch (e: Exception) {
                Napier.e("fetchMovies", e)
                _isLoading.emit(false)
            }
        }
    }

    fun onToggleGridPage() {
        viewModelScope.launch {
            _isGridPage.emit(_isGridPage.value.not())
        }
    }
}