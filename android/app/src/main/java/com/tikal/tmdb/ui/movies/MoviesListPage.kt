package com.tikal.tmdb.ui.movies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tikal.tmdb.model.Movie

@Composable
fun MoviesListPage(uiState: MoviesUiState) {
    val movies by uiState.movies.observeAsState()
    val scrollState = rememberLazyListState()

    MaterialTheme {
        LazyColumn(
            state = scrollState
        ) {
            items(items = movies.orEmpty()) { movie ->
                MovieListTile(movie, onMovieClicked = uiState::onMovieClicked)
            }
        }
    }
}

@Preview
@Composable
private fun MoviesViewPreview() {
    val uiState = object : MoviesUiState {
        override val isLoading: LiveData<Boolean> = MutableLiveData(false)
        override val movieDetails: LiveData<Movie?> = MutableLiveData(null)
        override val movies: LiveData<List<Movie>?> = MutableLiveData(listOf(movie550, movie550))
        override fun onMovieClicked(movie: Movie) = Unit
    }
    MoviesListPage(uiState)
}