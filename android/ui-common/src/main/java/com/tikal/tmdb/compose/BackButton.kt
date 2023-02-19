package com.tikal.tmdb

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BackButton(navController: NavController) {
    val currentEntry = navController.currentBackStackEntry
    val previousEntry = navController.previousBackStackEntry
    if ((currentEntry != null) && (previousEntry != null)) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

