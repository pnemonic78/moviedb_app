package com.tikal.tmdb

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.tikal.tmdb.compose.AppTheme
import com.tikal.tmdb.compose.CornerSizeLarge
import com.tikal.tmdb.compose.CornerSizeLargeDp
import com.tikal.tmdb.compose.CornerSizeMedium
import com.tikal.tmdb.compose.CornerSizeMediumDp
import com.tikal.tmdb.compose.CornerSizeZero
import com.tikal.tmdb.compose.thumbnailPainter
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.movies.OnMovieClickCallback
import com.tikal.tmdb.ui.common.R
import kotlin.math.max

private const val parallaxFactor = 0.85f
private const val posterAspectRatio = 1.5f

@Composable
fun MovieGridTile(
    modifier: Modifier = Modifier,
    movie: MovieEntity,
    onMovieClicked: OnMovieClickCallback
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val posterWidth = dimensionResource(id = R.dimen.posterWidth)
    val posterWidthPx = with(density) { posterWidth.toPx() }
    val thumbnailWidthState = remember { mutableStateOf(posterWidthPx) }
    val thumbnailWidthPx = max(posterWidthPx, thumbnailWidthState.value)
    val thumbnailWidth = with(density) { thumbnailWidthPx.toDp() }
    val thumbnailHeight = thumbnailWidth * posterAspectRatio
    val imageHeight = thumbnailHeight * parallaxFactor
    val imageHeightPx = with(density) { imageHeight.toPx() }
    val thumbnailPainter = movie.thumbnailPainter(
        context = context,
        width = thumbnailWidthState.value.toInt(),
        height = imageHeightPx.toInt()
    )

    val textTheme = MaterialTheme.typography

    Card(
        modifier = modifier
            .clickable { onMovieClicked(movie) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(
                        MaterialTheme.shapes.medium.copy(
                            bottomStart = CornerSizeZero,
                            bottomEnd = CornerSizeZero
                        )
                    )
                    .onSizeChanged { size ->
                        if (size.width > 0) {
                            thumbnailWidthState.value = size.width.toFloat()
                        }
                    },
                painter = thumbnailPainter,
                contentDescription = "poster",
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = movie.title + "\n",
                style = textTheme.subtitle1.copy(fontWeight = FontWeight.Medium),
                maxLines = 2
            )
            RatingBar(
                modifier = Modifier.padding(top = 8.dp),
                value = movie.voteAverage / 2,
                config = RatingBarConfig()
                    .isIndicator(true)
                    .numStars(5)
                    .size(24.dp)
                    .stepSize(StepSize.HALF)
                    .style(RatingBarStyle.HighLighted),
                onValueChange = {},
                onRatingChanged = {}
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = movie.releaseDate?.let {
                    DateUtils.formatDateTime(
                        context,
                        it.timeInMillis,
                        DateUtils.FORMAT_SHOW_DATE
                    )
                }.orEmpty(),
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCC00CC, widthDp = 200)
@Composable
private fun ThisPreview() {
    AppTheme {
        MovieGridTile(movie = movie550) {}
    }
}
