package com.tikal.tmdb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.tikal.tmdb.R
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.ui.moviedetails.MovieDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel by viewModels<MoviesViewModel>()
    private var progressBar: ContentLoadingProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(android.R.id.progress)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MoviesListPage(viewModel)
        }

        val owner: LifecycleOwner = viewLifecycleOwner
        viewModel.isLoading.observe(owner) { isLoading ->
            showLoadingIndicator(isLoading)
        }
        viewModel.movieDetails.observe(owner) { movie ->
            if (movie != null) showMovieDetails(movie)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMovies(false)
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
            putLong(MovieDetailFragment.EXTRA_MOVIE_ID, movie.id)
        }
        findNavController().navigate(R.id.movieDetailFragment, args)
        viewModel.onMovieDetailsShown(movie)
    }
}