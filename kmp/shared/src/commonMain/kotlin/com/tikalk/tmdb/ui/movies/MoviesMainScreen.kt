package com.tikalk.tmdb.ui.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tikalk.tmdb.compose.SimpleScreen
import com.tikalk.tmdb.inject.Inject
import com.tikalk.tmdb.moviedetails.MovieDetailsScreen
import com.tikalk.tmdb.moviedetails.MovieDetailsViewModel
import com.tikalk.tmdb.moviedetails.MoviePosterScreen
import com.tikalk.tmdb.movies.MoviesPageViewModel
import com.tikalk.tmdb.now.MoviesNowPlayingScreen
import com.tikalk.tmdb.popular.MoviesPopularScreen
import com.tikalk.tmdb.top_rated.MoviesTopRatedScreen
import com.tikalk.tmdb.upcoming.MoviesUpcomingScreen
import movie_db_kmp.shared.generated.resources.Res
import movie_db_kmp.shared.generated.resources.title
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoviesMainScreen() {
    val viewModel = viewModel { MainViewModel(Inject.repository) }
    val viewModelDetails = viewModel {
        MovieDetailsViewModel(
            Inject.repository,
            posterScreenRoute = MoviesScreen.Poster.route
        )
    }

    MoviesMainScreen(viewModel = viewModel, viewModelDetails = viewModelDetails)
}

@Composable
fun MoviesMainScreen(
    viewModel: MainViewModel,
    viewModelDetails: MovieDetailsViewModel
) {
    val navController = rememberNavController()

    MoviesMainScreen(viewModel, viewModelDetails, navController)
}

@Composable
fun MoviesMainScreen(
    viewModel: MainViewModel,
    viewModelDetails: MovieDetailsViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MoviesScreen.Main.route
    ) {
        composable(
            route = "${MoviesScreen.Details.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            val movieId = it.arguments?.getLong("id") ?: 0L
            viewModelDetails.fetchMovie(movieId)

            MovieDetailsScreen(
                viewModel = viewModelDetails,
                navController = navController
            )
        }
        composable(route = MoviesScreen.Main.route) {
            MoviesMainScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = MoviesScreen.NowPlaying.route) {
            val viewModelPage =
                viewModel { MoviesPageViewModel(Inject.repository, MoviesScreen.Details.route) }

            MoviesNowPlayingScreen(
                viewModel = viewModelPage,
                navController = navController
            )
        }
        composable(route = MoviesScreen.Popular.route) {
            val viewModelPage =
                viewModel { MoviesPageViewModel(Inject.repository, MoviesScreen.Details.route) }

            MoviesPopularScreen(
                viewModel = viewModelPage,
                navController = navController
            )
        }
        composable(
            route = "${MoviesScreen.Poster.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            val movieId = it.arguments?.getLong("id") ?: 0L
            viewModelDetails.fetchMovie(movieId)

            MoviePosterScreen(
                viewModel = viewModelDetails,
                navController = navController
            )
        }
        composable(route = MoviesScreen.TopRated.route) {
            val viewModelPage =
                viewModel { MoviesPageViewModel(Inject.repository, MoviesScreen.Details.route) }

            MoviesTopRatedScreen(
                viewModel = viewModelPage,
                navController = navController
            )
        }
        composable(route = MoviesScreen.Upcoming.route) {
            val viewModelPage =
                viewModel { MoviesPageViewModel(Inject.repository, MoviesScreen.Details.route) }

            MoviesUpcomingScreen(
                viewModel = viewModelPage,
                navController = navController
            )
        }
    }
}

@Composable
private fun MoviesMainScreen(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    SimpleScreen(
        title = stringResource(Res.string.title),
        navController = navController
    ) { innerPadding ->
        MoviesMainPage(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            navController = navController
        )
    }
}
