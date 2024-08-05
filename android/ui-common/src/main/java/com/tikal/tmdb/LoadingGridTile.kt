package com.tikal.tmdb

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tikal.tmdb.compose.AppTheme
import com.tikal.tmdb.ui.common.R
import kotlin.math.max

private const val parallaxFactor = 0.85f
private const val posterAspectRatio = 1.5f

@Composable
fun LoadingGridTile(
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    val posterWidth = dimensionResource(id = R.dimen.posterWidth)
    val posterWidthPx = with(density) { posterWidth.toPx() }
    val thumbnailWidthState = remember { mutableFloatStateOf(posterWidthPx) }
    val thumbnailWidthPx = max(posterWidthPx, thumbnailWidthState.value)
    val thumbnailWidth = with(density) { thumbnailWidthPx.toDp() }
    val thumbnailHeight = thumbnailWidth * posterAspectRatio
    val imageHeight = thumbnailHeight * parallaxFactor

    val textTheme = MaterialTheme.typography

    Card(
        modifier = modifier
    ) {
        Box {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "\n",
                    style = textTheme.subtitle1.copy(fontWeight = FontWeight.Medium),
                    maxLines = 2
                )
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(24.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "",
                    maxLines = 1
                )
            }
            CircularProgressIndicator(
                modifier = Modifier
                    .size(thumbnailWidth)
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCC00CC, widthDp = 200)
@Composable
private fun ThisPreview() {
    AppTheme {
        LoadingGridTile()
    }
}
