package com.tmdbcodlab.android.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.model.Movie
import com.tmdbcodlab.android.ui.moviedetails.MovieDetailActivity

/**
 * Created by ronelg on 12/19/17.
 */
class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
    }

    fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movie.id)
        startActivity(intent)
    }

}