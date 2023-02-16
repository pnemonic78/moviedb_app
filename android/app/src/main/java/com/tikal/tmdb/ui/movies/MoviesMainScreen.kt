package com.tikal.tmdb.ui.movies

import android.annotation.SuppressLint
import android.net.Uri
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tikal.tmdb.R
import com.tikal.tmdb.data.model.DatesEntity
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.model.MoviesPageEntity
import com.tikal.tmdb.data.model.MoviesPageType
import com.tikal.tmdb.movie550
import com.tikal.tmdb.moviedetails.MovieDetailsPage
import com.tikal.tmdb.moviedetails.MoviePosterPage
import com.tikal.tmdb.moviedetails.movie550Details
import com.tikal.tmdb.now.MoviesNowPlayingPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesMainScreen(
    viewState: MainViewState
) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val navBackStackEntryValue = navBackStackEntry.value
    val title = viewState.title.collectAsState()
    val isGrid = viewState.isGridPage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title.value) },
                navigationIcon = {
                    BackButton(navController, navBackStackEntryValue)
                },
                actions = {
                    ToggleGridButton(viewState, navBackStackEntryValue, isGrid.value)
                }
            )
        }
    ) {
        NavHost(navController, startDestination = MoviesScreen.Main.route) {
            composable(
                "${MoviesScreen.Details.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) {
                MovieDetailsPage(
                    viewState,
                    navController,
                    it.arguments?.getLong("id") ?: 0L
                )
            }
            composable(MoviesScreen.Main.route) {
                MoviesMainPage(viewState, navController)
            }
            composable(MoviesScreen.NowPlaying.route) {
                MoviesNowPlayingPage(viewState, navController)
            }
            composable(
                "${MoviesScreen.Poster.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) {
                MoviePosterPage(
                    viewState,
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
    viewState: MainViewState,
    currentEntry: NavBackStackEntry?,
    isGrid: Boolean
) {
    val isNowPlaying = currentEntry?.destination?.route == MoviesScreen.NowPlaying.route

    if (isNowPlaying) {
        if (isGrid) {
            IconButton(onClick = viewState::onToggleGridPage) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null
                )
            }
        } else {
            IconButton(onClick = viewState::onToggleGridPage) {
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
    val moviesList = listOf(movie550, movie550)
    val page = MoviesPage(
        page = MoviesPageEntity(
            dates = DatesEntity(),
            page = 1,
            totalPages = 1,
            totalResult = moviesList.size,
            type = MoviesPageType.NOW_PLAYING
        ),
        movies = moviesList
    )

    val nowPlayingViewState = object : MoviesCarouselViewState {
        override val movies: StateFlow<List<MoviesPage>> = MutableStateFlow(listOf(page))
        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
    }
    val viewState = object : MainViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val title: MutableStateFlow<String> = MutableStateFlow("Main")
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(false)
        override val movies: StateFlow<List<MovieEntity>> = MutableStateFlow(moviesList)

        override fun movieDetails(movieId: Long): StateFlow<MovieEntity?> =
            MutableStateFlow(movie550Details)

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onPosterClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onLinkClicked(movie: MovieEntity, uri: Uri) = Unit
        override fun onToggleGridPage() = Unit
        override fun onTitleClicked(type: MoviesPageType, navController: NavHostController) = Unit

        override val nowPlayingViewState: MoviesCarouselViewState = nowPlayingViewState
    }
    MaterialTheme {
        MoviesMainScreen(viewState)
    }
}