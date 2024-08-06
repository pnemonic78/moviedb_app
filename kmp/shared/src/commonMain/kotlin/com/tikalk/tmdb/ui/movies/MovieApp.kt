package com.tikalk.tmdb.ui.movies

import androidx.compose.runtime.Composable
import com.tikalk.tmdb.inject.Inject
import com.tikalk.tmdb.ui.MoviesViewModel

@Composable
fun App() {
    val viewModel = MoviesViewModel(Inject.repository)
    MoviesRoute(
        viewModel = viewModel,
        onClick = { movie ->
            println("clicked movie $movie")
        }
    )
}
