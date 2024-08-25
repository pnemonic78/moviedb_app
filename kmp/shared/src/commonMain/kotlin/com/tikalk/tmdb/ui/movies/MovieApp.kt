package com.tikalk.tmdb.ui.movies

import androidx.compose.runtime.Composable
import com.tikalk.tmdb.inject.Inject
import com.tikalk.tmdb.ui.MoviesViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Composable
fun App() {
    Napier.base(DebugAntilog())

    val viewModel = MoviesViewModel(Inject.repository)
    MoviesRoute(viewModel = viewModel)
}
