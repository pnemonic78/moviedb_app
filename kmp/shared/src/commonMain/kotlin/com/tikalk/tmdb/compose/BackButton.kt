package com.tikalk.tmdb.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

