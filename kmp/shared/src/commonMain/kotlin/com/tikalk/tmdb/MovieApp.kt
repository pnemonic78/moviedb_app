package com.tikalk.tmdb

import androidx.compose.runtime.Composable
import com.tikalk.tmdb.ui.movies.MoviesMainScreen
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Composable
fun App() {
    Napier.base(DebugAntilog())
    MoviesMainScreen()
}
