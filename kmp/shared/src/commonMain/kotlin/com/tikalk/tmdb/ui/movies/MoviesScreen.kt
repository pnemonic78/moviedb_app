package com.tikalk.tmdb.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.tikalk.Result
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.movies.MoviesPageViewState
import com.tikalk.tmdb.movies.OnMovieClickCallback
import com.tikalk.tmdb.ui.UiState
import com.tikalk.tmdb.ui.components.Loader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    uiState: UiState<List<MovieEntity>>,
    onClick: OnMovieClickCallback
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state: Result<List<MovieEntity>> = uiState.state) {
                is Result.Loading -> {
                    Loader(modifier = Modifier.padding(paddingValues))
                }

                is Result.Success -> {
                    state.data?.let {
                        SuccessState(it, paddingValues, onClick)
                    } ?: ErrorState()
                }

                is Result.Error -> ErrorState()
            }
        }
    }
}

@Composable
private fun SuccessState(
    movies: List<MovieEntity>,
    paddingValues: PaddingValues,
    onClick: OnMovieClickCallback
) {
    val viewState = object : MoviesPageViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(true)
        override val movies: Flow<PagingData<MovieEntity>> = flowOf(PagingData.from(movies))

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) =
            onClick(movie)

        override fun onToggleLayout() = Unit
    }
    val navController = rememberNavController()
    MoviesGridPage(
        modifier = Modifier.padding(paddingValues),
        viewState = viewState,
        navController = navController
    )
}

@Composable
private fun ErrorState() {
    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.errorContainer)) {
    }
}
