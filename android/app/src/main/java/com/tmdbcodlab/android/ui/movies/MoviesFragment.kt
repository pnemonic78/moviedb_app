package com.tmdbcodlab.android.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tmdbcodlab.android.MyApplication
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.data.TmdbRepository
import com.tmdbcodlab.android.model.Movie
import javax.inject.Inject

/**
 * Created by ronelg on 12/19/17.
 */
class MoviesFragment : Fragment(), MoviesContract.View, MoviesAdapter.MovieListener {

    @Inject
    lateinit var repository: TmdbRepository

    override var presenter: MoviesContract.Presenter? = null
    private var progressBar: ContentLoadingProgressBar? = null
    private val adapter: MoviesAdapter = MoviesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appComponent.inject(this)
        presenter = MoviesPresenter(repository, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(android.R.id.progress)
        initList(view.findViewById(android.R.id.list))
    }

    override fun onStart() {
        super.onStart()
        presenter?.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter?.unsubscribe()
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
        presenter?.onMovieClicked(movie)
    }

    override fun showMovieDetails(movie: Movie) {
        (activity as MoviesActivity).showMovieDetails(movie)
    }

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}