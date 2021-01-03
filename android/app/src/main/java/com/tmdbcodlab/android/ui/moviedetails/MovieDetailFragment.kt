package com.tmdbcodlab.android.ui.moviedetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.tmdbcodlab.android.MyApplication
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.api.TmdbApi
import com.tmdbcodlab.android.data.TmdbRepository
import com.tmdbcodlab.android.model.MovieDetails
import javax.inject.Inject

/**
 * Created by ronelg on 12/19/17.
 */
class MovieDetailFragment : Fragment(), MovieDetailsContract.View {

    @Inject
    lateinit var repository: TmdbRepository

    override var presenter: MovieDetailsContract.Presenter? = null
    private var movieId: Long? = null
    private var progressBar: ContentLoadingProgressBar? = null
    private var title: TextView? = null
    private var summary: TextView? = null
    private var popularity: ProgressBar? = null
    private var poster: ImageView? = null
    private var date: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appComponent.inject(this)
        presenter = MovieDetailsPresenter(repository, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
    }

    override fun onStart() {
        super.onStart()
        presenter?.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter?.unsubscribe()
    }

    override fun onResume() {
        super.onResume()
        if (movieId != null) {
            presenter?.loadMovie(movieId!!)
        }
    }

    fun setMovieId(movieId: Long) {
        this.movieId = movieId
        presenter?.loadMovie(movieId)
    }

    override fun showLoadingIndicator(active: Boolean) {
        if (active) {
            progressBar?.show()
        } else {
            progressBar?.hide()
        }
    }

    override fun showMovie(movie: MovieDetails) {
        this.movieId = null

        title!!.text = movie.title
        summary!!.text = movie.overview
        popularity!!.progress = (movie.voteAverage * 10f).toInt()
        date!!.text = DateUtils.formatDateTime(context, movie.releaseDate.time, DateUtils.FORMAT_SHOW_DATE)

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
        fun newInstance(movieId: Long): MovieDetailFragment {
            val instance = MovieDetailFragment()
            instance.setMovieId(movieId)
            return instance
        }
    }
}