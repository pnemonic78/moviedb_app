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
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tikal.tmdb.R
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.movie550
import com.tikal.tmdb.moviedetails.MovieDetailsPage
import com.tikal.tmdb.moviedetails.MoviePosterPage
import com.tikal.tmdb.moviedetails.movie550Details
import com.tikal.tmdb.now.MoviesNowPlayingPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesMainPage(
    uiState: MainState
) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val navBackStackEntryValue = navBackStackEntry.value
    val title = uiState.title.collectAsState()
    val isGrid = uiState.isGridPage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title.value) },
                navigationIcon = {
                    BackButton(navController, navBackStackEntryValue)
                },
                actions = {
                    ToggleGridButton(uiState, navBackStackEntryValue, isGrid.value)
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
                MoviesNowPlayingPage(uiState, navController)
            }
            composable(
                "${MoviesScreen.Poster.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) {
                MoviePosterPage(
                    uiState,
                    it.arguments?.getLong("id") ?: 0L
                )
            }
        }
    }
}

@Composable
private fun BackButton(navController: NavController, currentEntry: NavBackStackEntry?) {
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

@Composable
private fun ToggleGridButton(
    uiState: MainState,
    currentEntry: NavBackStackEntry?,
    isGrid: Boolean
) {
    val isNowPlaying = currentEntry?.destination?.route == MoviesScreen.NowPlaying.route

    if (isNowPlaying) {
        if (isGrid) {
            IconButton(onClick = uiState::onToggleGridPage) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null
                )
            }
        } else {
            IconButton(onClick = uiState::onToggleGridPage) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_grid_on),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val mainState = object : MainState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Main")
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(false)
        override val movies: StateFlow<List<MovieEntity>> =
            MutableStateFlow(listOf(movie550, movie550))

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onToggleGridPage() = Unit

        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            MutableStateFlow(movie550Details)

        override fun onPosterClicked(movie: MovieEntity, navController: NavController) = Unit
    }
    MaterialTheme {
        MoviesMainPage(mainState)
    }
}