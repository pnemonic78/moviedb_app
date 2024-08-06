package com.tikalk.tmdb.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tikalk.tmdb.compose.AppTheme

private const val parallaxFactor = 0.85f
private const val posterAspectRatio = 1.5f
private val posterWidth = 160.dp

@Composable
fun LoadingListTile(
    modifier: Modifier = Modifier
) {
    val thumbnailWidth = posterWidth
    val thumbnailHeight = thumbnailWidth * posterAspectRatio
    val imageHeight = thumbnailHeight * parallaxFactor

    Card(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(thumbnailHeight)
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCC00CC, widthDp = 300)
@Composable
private fun ThisPreview() {
    AppTheme {
        LoadingListTile()
    }
}