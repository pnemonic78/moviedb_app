package com.tikalk.tmdb.ui.movies

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.tikalk.tmdb.movies.MoviesScreen
import com.tikalk.tmdb.ui.MoviesViewModel

@Composable
fun MoviesRoute(
    viewModel: MoviesViewModel
) {
    val navController = rememberNavController()
    MoviesScreen(
        title = "TMDB KMP",
        viewState = viewModel,
        navController = navController
    )
}
