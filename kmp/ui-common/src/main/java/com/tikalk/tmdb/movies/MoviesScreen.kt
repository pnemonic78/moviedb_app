package com.tikalk.tmdb.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.tikalk.Result
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.BackButton
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ui.Loader
import com.tikalk.tmdb.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import movie_db_kmp.ui_common.generated.resources.Res
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(title: String, viewState: MoviesPageViewState, navController: NavController) {
    val isGridPage = viewState.isGridPage.collectAsState()
    val isGrid = isGridPage.value

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                BackButton(navController = navController)
            },
            actions = {
                ToggleLayoutButton(viewState, isGrid)
            }
        )
    }) { innerPadding ->
        if (isGrid) {
            MoviesGridPage(modifier = Modifier.padding(innerPadding), viewState, navController)
        } else {
            MoviesListPage(modifier = Modifier.padding(innerPadding), viewState, navController)
        }
    }
}

@Composable
private fun ToggleLayoutButton(
    viewState: MoviesPageViewState,
    isGrid: Boolean = false
) {
    if (isGrid) {
        IconButton(onClick = viewState::onToggleLayout) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = null
            )
        }
    } else {
//        IconButton(onClick = viewState::onToggleLayout) {
//            Icon(
//                imageVector = vectorResource(Res.drawable.ic_grid_on),
//                contentDescription = null
//            )
//        }
    }
}

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
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state: Result<List<MovieEntity>> = uiState.state) {
                is Result.Loading -> {
                    Loader(modifier = Modifier.padding(innerPadding))
                }

                is Result.Success -> {
                    state.data?.let {
                        SuccessState(it, innerPadding, onClick)
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

@Preview
@Composable
private fun ThisPreview() {
    val title = "Movies"
    val viewState = object : MoviesPageViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(false)
        override val movies: Flow<PagingData<MovieEntity>> = moviesPreview

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onToggleLayout() = Unit
    }
    val navController = rememberNavController()

    AppTheme {
        MoviesScreen(title, viewState, navController)
    }
}