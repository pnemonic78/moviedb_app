package com.tikal.tmdb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tikal.tmdb.R
import com.tikal.tmdb.model.Movie

class MoviesAdapter(private val listener: MovieListener? = null) :
    RecyclerView.Adapter<MovieViewHolder>() {

    var data: List<Movie> = emptyList()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val source = data[position]

        holder.bind(source)
    }

    fun setItems(items: List<Movie>) {
        this.data = items
        notifyDataSetChanged()
    }

    interface MovieListener {
        fun onMovieClicked(movie: Movie)
    }
}