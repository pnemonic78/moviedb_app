package com.tikal.tmdb.ui.movies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tikal.tmdb.R
import com.tikal.tmdb.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
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
            AppTheme {
                MoviesMainScreen(viewModel)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                showLoadingIndicator(isLoading)
            }
        }
        viewModel.launchUri.observe(viewLifecycleOwner) { uri ->
            if (uri != null) launchUri(uri)
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

    private fun launchUri(uri: Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: Exception) {
            Timber.e(e, "launch intent failed")
        }
    }
}