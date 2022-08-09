package com.tikal.tmdb.ui.movies

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.movie550
import com.tikal.tmdb.moviedetails.MovieDetailsPage
import com.tikal.tmdb.moviedetails.movie550Details
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesMainPage(
    uiState: MainState
) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val title = uiState.title.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title.value) },
                navigationIcon = {
                    val currentEntry = navBackStackEntry.value
                    val previousEntry = navController.previousBackStackEntry
                    if ((currentEntry != null) && (previousEntry != null)) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        }
    ) {
        NavHost(navController, startDestination = MoviesScreen.NowPlaying.route) {
            composable(
                "${MoviesScreen.Details.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) {
                MovieDetailsPage(
                    uiState,
                    navController,
                    it.arguments?.getLong("id") ?: 0L
                )
            }
            composable(MoviesScreen.NowPlaying.route) {
                MoviesListPage(uiState, navController)
            }
        }
    }
}

@Preview
@Composable
private fun MoviesMainPagePreview() {
    val mainState = object : MainState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Main")
        override val movies: StateFlow<List<MovieEntity>> = MutableStateFlow(listOf(movie550, movie550))
        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            MutableStateFlow(movie550Details)
    }
    MaterialTheme {
        MoviesMainPage(mainState)
    }
}