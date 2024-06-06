package com.tikalk.tmdb.ui.movies

import androidx.compose.runtime.Composable
import com.tikal.tmdb.inject.Inject
import com.tikalk.tmdb.ui.MoviesViewModel

@Composable
fun App() {
    val viewModel = MoviesViewModel(Inject.repository)
    MoviesRoute(
        viewModel = viewModel,
        onClick = { id, title ->
            println("clicked movie $id $title")
        }
    )
}
