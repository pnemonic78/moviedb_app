package com.tikalk.tmdb.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.CornerSizeZero
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.movies.movie550
import com.tikalk.tmdb.movies.OnMovieClickCallback
import com.tikalk.tmdb.movies.posterPainter
import io.kamel.image.KamelImage
import java.text.DateFormat
import java.util.Date
import kotlin.math.max

@Composable
fun MovieGridTile(
    modifier: Modifier = Modifier,
    movie: MovieEntity,
    onMovieClicked: OnMovieClickCallback
) {
    val density = LocalDensity.current

    val posterWidth = dimen.posterWidth
    val posterWidthPx = with(density) { posterWidth.toPx() }
    val thumbnailWidthState = remember { mutableFloatStateOf(posterWidthPx) }
    val thumbnailWidthPx = max(posterWidthPx, thumbnailWidthState.floatValue)
    val thumbnailWidth = with(density) { thumbnailWidthPx.toDp() }
    val thumbnailHeight = thumbnailWidth * posterAspectRatio
    val imageHeight = thumbnailHeight * parallaxFactor
    val imageHeightPx = with(density) { imageHeight.toPx() }
    val thumbnailPainter = movie.posterPainter(
        width = thumbnailWidthState.floatValue.toInt(),
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
            KamelImage(
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
                            thumbnailWidthState.floatValue = size.width.toFloat()
                        }
                    },
                resource = thumbnailPainter,
                contentDescription = "poster",
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                    .fillMaxWidth(),
                text = movie.title + "\n",
                style = textTheme.titleMedium.copy(fontWeight = FontWeight.Medium),
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            RatingBar(
                modifier = Modifier.padding(top = 4.dp),
                value = (movie.voteAverage / 2).toFloat(),
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
                modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp),
                text = movie.releaseDate?.let {
                    DateFormat.getDateInstance().format(Date(it.timeInMillis))
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
        MovieGridTile(movie = movie550) {
            println("clicked movie")
        }
    }
}