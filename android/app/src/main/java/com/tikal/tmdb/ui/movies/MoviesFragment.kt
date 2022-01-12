package com.tikal.tmdb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tikal.tmdb.R
import com.tikal.tmdb.data.TmdbRepository
import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.ui.moviedetails.MovieDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesContract.View, MoviesAdapter.MovieListener {

    @Inject
    lateinit var repository: TmdbRepository

    override lateinit var presenter: MoviesContract.Presenter
    private var progressBar: ContentLoadingProgressBar? = null
    private val adapter: MoviesAdapter = MoviesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MoviesPresenter(repository, this)
    }

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
        initList(view.findViewById(android.R.id.list))
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun showLoadingIndicator(active: Boolean) {
        if (active) {
            progressBar?.show()
        } else {
            progressBar?.hide()
        }
    }

    private fun initList(list: RecyclerView) {
        list.adapter = adapter
    }

    override fun showMovies(data: List<Movie>) {
        adapter.setItems(data)
    }

    override fun onMovieClicked(movie: Movie) {
        presenter.onMovieClicked(movie)
    }

    override fun showMovieDetails(movie: Movie) {
        val args = Bundle().apply {
            putLong(MovieDetailFragment.EXTRA_MOVIE_ID, movie.id)
        }
        findNavController().navigate(R.id.movieDetailFragment, args)
    }
}