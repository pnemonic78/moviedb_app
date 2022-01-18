package com.tikal.tmdb.ui.movies

import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tikal.tmdb.R
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.model.Movie

class MovieViewHolder(itemView: View, private val listener: MoviesAdapter.MovieListener? = null) :
    RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(android.R.id.title)
    private val summary: TextView = itemView.findViewById(android.R.id.summary)
    private val popularity: ProgressBar = itemView.findViewById(R.id.popularity)
    private val poster: ImageView = itemView.findViewById(R.id.poster)
    private val date: TextView = itemView.findViewById(R.id.date)

    fun bind(movie: Movie) {
        val context = itemView.context

        title.text = movie.title
        summary.text = movie.overview
        popularity.progress = (movie.voteAverage * 10f).toInt()

        val releaseDate = movie.releaseDate
        if (releaseDate != null) {
            date.text = DateUtils.formatDateTime(
                context,
                releaseDate.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE
            )
        } else {
            date.text = null
        }

        TmdbApi.showPoster(movie, poster)

        itemView.setOnClickListener {
            listener?.onMovieClicked(movie)
        }
    }
}