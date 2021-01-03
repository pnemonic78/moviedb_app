package com.tmdbcodlab.android.ui.movies

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.api.TmdbApi
import com.tmdbcodlab.android.model.Movie

/**
 * @author moshe on 2018/02/11.
 */
class MoviesAdapter(var listener: MovieListener? = null) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var data: List<Movie> = emptyList()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val source = data[position]

        holder.bind(source)
    }

    fun setItems(items: List<Movie>) {
        this.data = items
        notifyDataSetChanged()
    }

    fun setMovieListener(listener: MovieListener) {
        this.listener = listener
    }

    interface MovieListener {
        fun onMovieClicked(movie: Movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val title: TextView = itemView.findViewById(android.R.id.title)
        private val summary: TextView = itemView.findViewById(android.R.id.summary)
        private val popularity: ProgressBar = itemView.findViewById(R.id.popularity)
        private val poster: ImageView = itemView.findViewById(R.id.poster)
        private val date: TextView = itemView.findViewById(R.id.date)

        private var movie: Movie? = null

        fun bind(movie: Movie) {
            this.movie = movie
            val context = itemView.context

            title.text = movie.title
            summary.text = movie.overview
            popularity.progress = (movie.voteAverage * 10f).toInt()
            date.text = DateUtils.formatDateTime(context, movie.releaseDate.time, DateUtils.FORMAT_SHOW_DATE)

            TmdbApi.showPoster(movie, poster)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if ((v == itemView) && (movie != null)) {
                listener?.onMovieClicked(movie!!)
            }
        }
    }
}