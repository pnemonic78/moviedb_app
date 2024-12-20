package com.tikalk.tmdb.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.BackButton
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.moviesPreview
import com.tikalk.tmdb.ui.common.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
        IconButton(onClick = viewState::onToggleLayout) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_grid_on),
                contentDescription = null
            )
        }
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