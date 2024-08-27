package com.tikalk.tmdb.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
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

@Composable
fun MovieListTile(
    modifier: Modifier = Modifier,
    movie: MovieEntity,
    onMovieClicked: OnMovieClickCallback
) {
    val thumbnailSize = remember { mutableStateOf(IntSize.Zero) }
    val thumbnailWidth = dimen.posterWidth
    val thumbnailHeight = thumbnailWidth * posterAspectRatio
    val imageWidth = thumbnailWidth * 1f
    val imageHeight = thumbnailHeight * parallaxFactor
    val thumbnailPainter = movie.posterPainter(size = thumbnailSize.value)

    val textTheme = MaterialTheme.typography

    Card(
        modifier = modifier
            .clickable { onMovieClicked(movie) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            KamelImage(
                resource = thumbnailPainter,
                contentDescription = "poster",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(imageWidth)
                    .height(imageHeight)
                    .clip(
                        MaterialTheme.shapes.medium.copy(
                            topEnd = CornerSizeZero,
                            bottomEnd = CornerSizeZero
                        )
                    )
                    .onSizeChanged { size -> thumbnailSize.value = size }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            ) {
                Text(
                    text = movie.title,
                    style = textTheme.titleMedium.copy(fontWeight = FontWeight.Medium),
                    maxLines = 2
                )
                RatingBar(
                    modifier = Modifier.padding(top = 8.dp),
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
                    modifier = Modifier.padding(top = 8.dp),
                    text = movie.releaseDate?.let {
                        DateFormat.getDateInstance().format(Date(it.timeInMillis))
                    }.orEmpty()
                )
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    text = movie.overview.orEmpty(),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCC00CC)
@Composable
private fun ThisPreview() {
    AppTheme {
        MovieListTile(movie = movie550) {}
    }
}