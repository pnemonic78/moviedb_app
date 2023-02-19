package com.tikal.tmdb.ui.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tikal.tmdb.R
import com.tikal.tmdb.compose.SimpleScreen
import com.tikal.tmdb.moviedetails.MovieDetailsScreen
import com.tikal.tmdb.moviedetails.MoviePosterScreen
import com.tikal.tmdb.now.MoviesNowPlayingScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesMainScreen(
    viewState: MainViewState
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MoviesScreen.Main.route
    ) {
        composable(
            route = "${MoviesScreen.Details.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            MovieDetailsScreen(
                viewState = viewState.movieDetailsViewState,
                navController = navController,
                movieId = it.arguments?.getLong("id") ?: 0L
            )
        }
        composable(route = MoviesScreen.Main.route) {
            MoviesMainScreen(
                viewState = viewState,
                navController = navController
            )
        }
        composable(route = MoviesScreen.NowPlaying.route) {
            MoviesNowPlayingScreen(viewState.moviesPageViewState, navController)
        }
        composable(
            route = "${MoviesScreen.Poster.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            MoviePosterScreen(
                viewState = viewState.movieDetailsViewState,
                navController = navController,
                movieId = it.arguments?.getLong("id") ?: 0L
            )
        }
    }
}

@Composable
private fun MoviesMainScreen(
    viewState: MainViewState,
    navController: NavHostController
) {
    val title = stringResource(id = R.string.title)

    SimpleScreen(
        title = title,
        navController = navController
    ) { innerPadding ->
        MoviesMainPage(
            modifier = Modifier.padding(innerPadding),
            viewState = viewState.moviesMainViewState,
            navController = navController
        )
    }
}
