package com.tikal.tmdb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tikal.tmdb.R
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.moviedetails.MovieDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel by viewModels<MoviesViewModel>()
    private var progressBar: ContentLoadingProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_compose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(android.R.id.progress)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MaterialTheme {
                MoviesListPage(viewModel)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                showLoadingIndicator(isLoading)
            }
        }
        lifecycleScope.launch {
            viewModel.movieDetails.collect { movie ->
                if (movie != null) showMovieDetails(movie)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMovies()
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            progressBar?.show()
        } else {
            progressBar?.hide()
        }
    }

    private fun showMovieDetails(movie: Movie) {
        val args = Bundle().apply {
            putLong(MovieDetailsFragment.EXTRA_MOVIE_ID, movie.id)
        }
        findNavController().navigate(R.id.movieDetailFragment, args)
        viewModel.onMovieDetailsShown(movie)
    }
}