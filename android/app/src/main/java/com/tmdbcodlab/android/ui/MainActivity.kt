package com.tmdbcodlab.android.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.ui.movies.MoviesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, MoviesActivity::class.java))
        finish()
    }
}
