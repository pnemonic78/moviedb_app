package com.tikalk.tmdb.ui.movies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tikal.Result
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.ui.UiState
import com.tikalk.tmdb.ui.components.Loader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    uiState: UiState<List<MovieEntity>>,
    onClick: (Long, String) -> Unit
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
    onClick: (Long, String) -> Unit
) {
    AnimatedVisibility(
        visible = movies.isNotEmpty(),
        modifier = Modifier.padding(paddingValues)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            content = {
                items(items = movies, key = { it.id }) {
                    MovieCell(
                        movie = it,
                        onClick = onClick
                    )
                }
            }
        )
    }
}

@Composable
private fun ErrorState() {
    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.errorContainer)) {
    }
}
