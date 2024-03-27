package com.tikal.tmdb.android

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.tikal.tmdb.compose.TmdbApp
import com.tikal.tmdb.inject.Inject

@Composable
fun GreetingView(text: String) {
    Text(text = text)

    LaunchedEffect(text) {
        val dataSource = Inject.getRepository
        println("±!@ dataSource=$dataSource")
//        val movies = dataSource.getMoviesNowPlaying()
//        println("±!@ movies=$movies")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    TmdbApp {
        GreetingView("Hello, Android!")
    }
}
