package com.tikalk.tmdb.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tikalk.tmdb.compose.BackButton
import com.tikalk.tmdb.compose.OnClickCallback
import movie_db_kmp.ui_common.generated.resources.Res
import movie_db_kmp.ui_common.generated.resources.ic_grid_on
import org.jetbrains.compose.resources.vectorResource

@Composable
fun MoviesScreen(
    title: String,
    viewModel: MoviesPageViewModel,
    navController: NavController
) {
    val viewState = viewModel.uiState.collectAsState()

    MoviesScreen(
        title = title,
        viewState = viewState.value,
        navController = navController,
        onMovieClicked = { viewModel.onMovieClicked(it, navController) },
        onToggleLayout = viewModel::onToggleLayout
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    title: String,
    viewState: MoviesPageViewState,
    navController: NavController,
    onMovieClicked: OnMovieClickCallback,
    onToggleLayout: OnClickCallback
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = { BackButton(navController = navController) },
            actions = { ToggleLayoutButton(viewState, onToggleLayout) }
        )
    }) { innerPadding ->
        if (viewState.isGridPage) {
            MoviesGridPage(
                modifier = Modifier.padding(innerPadding),
                viewState = viewState,
                onMovieClicked = onMovieClicked
            )
        } else {
            MoviesListPage(
                modifier = Modifier.padding(innerPadding),
                viewState = viewState,
                onMovieClicked = onMovieClicked
            )
        }
    }
}

@Composable
private fun ToggleLayoutButton(
    viewState: MoviesPageViewState,
    onToggleLayout: OnClickCallback
) {
    if (viewState.isGridPage) {
        IconButton(onClick = onToggleLayout) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = null
            )
        }
    } else {
        IconButton(onClick = onToggleLayout) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_grid_on),
                contentDescription = null
            )
        }
    }
}

