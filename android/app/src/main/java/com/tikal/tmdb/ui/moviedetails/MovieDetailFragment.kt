package com.tikal.tmdb.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.tikal.tmdb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private var progressBar: ContentLoadingProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(android.R.id.progress)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MovieDetailsView(viewModel)
        }

        val owner: LifecycleOwner = viewLifecycleOwner
        viewModel.isLoading.observe(owner) { isLoading ->
            showLoadingIndicator(isLoading)
        }
    }

    override fun onStart() {
        super.onStart()

        val movieId = arguments?.getLong(EXTRA_MOVIE_ID, 0L) ?: 0L
        viewModel.loadMovie(movieId)
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            progressBar?.show()
        } else {
            progressBar?.hide()
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }
}