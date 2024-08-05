package com.tikal.tmdb.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SimpleErrorScreen(
    title: String,
    navController: NavController
) {
    SimpleScreen(
        title = title,
        navController = navController
    ) {
        SimpleErrorContent(modifier = Modifier.background(color = MaterialTheme.colorScheme.error))
    }
}

@Composable
fun SimpleErrorContent(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.background(color = MaterialTheme.colorScheme.error))
}
