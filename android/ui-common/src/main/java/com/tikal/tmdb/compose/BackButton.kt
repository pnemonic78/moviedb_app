package com.tikal.tmdb

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val currentEntry = navController.currentBackStackEntry
    val previousEntry = navController.previousBackStackEntry
    if ((currentEntry != null) && (previousEntry != null)) {
        IconButton(
            modifier = modifier,
            onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

