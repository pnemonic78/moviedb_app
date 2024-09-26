package com.tikalk.tmdb.moviedetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.tikalk.tmdb.compose.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterScreen(
    navController: NavController,
    posterPathSuffix: String?
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {
                BackButton(navController = navController)
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Color.Transparent
            ),
        )
    }) { innerPadding ->
        PosterPage(modifier = Modifier.padding(innerPadding), posterPathSuffix = posterPathSuffix)
    }
}

@Composable
fun PosterPage(
    modifier: Modifier = Modifier,
    posterPathSuffix: String?
) {
    PosterContent(
        modifier = modifier,
        posterPathSuffix = posterPathSuffix
    )
}
