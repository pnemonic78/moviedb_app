package com.tikal.tmdb.ui.moviedetails

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.compose.ui.platform.ComposeView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.tikal.tmdb.R
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.model.MovieDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private var progressBar: ContentLoadingProgressBar? = null
    private var title: TextView? = null
    private var summary: TextView? = null
    private var popularity: ProgressBar? = null
    private var poster: ImageView? = null
    private var date: TextView? = null

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
        title = view.findViewById(android.R.id.title)
        summary = view.findViewById(android.R.id.summary)
        popularity = view.findViewById(R.id.popularity)
        poster = view.findViewById(R.id.poster)
        date = view.findViewById(R.id.date)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MovieDetailsView(viewModel)
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

    private fun showMovieDetails(movie: MovieDetails) {
        title!!.text = movie.title
        summary!!.text = movie.overview
        popularity!!.progress = (movie.voteAverage * 10f).toInt()
        val releaseDate = movie.releaseDate
        if (releaseDate != null) {
            date!!.text = DateUtils.formatDateTime(
                context,
                releaseDate.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE
            )
        } else {
            date!!.text = null
        }

        val imageView = poster!!
        if (imageView.measuredWidth > 0) {
            TmdbApi.showPoster(movie, imageView)
        } else {
            val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    TmdbApi.showPoster(movie, imageView)
                }
            }
            imageView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }
}