package com.tmdbcodlab.android.ui.moviedetails

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.tmdbcodlab.android.R

/**
 * Created by ronelg on 12/19/17.
 */
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getLongExtra(EXTRA_MOVIE_ID, 0L)

        val fragment = supportFragmentManager.findFragmentById(R.id.movie_details) as MovieDetailFragment
        fragment.setMovieId(movieId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}