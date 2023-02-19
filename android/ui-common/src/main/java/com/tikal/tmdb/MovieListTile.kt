package com.tikal.tmdb

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.tikal.tmdb.compose.thumbnailPainter
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.movies.OnMovieClickCallback
import com.tikal.tmdb.ui.common.R

private const val parallaxFactor = 0.85f
private const val posterAspectRatio = 1.5f

@Composable
fun MovieListTile(
    movie: MovieEntity,
    modifier: Modifier = Modifier,
    onMovieClicked: OnMovieClickCallback
) {
    val context = LocalContext.current

    val thumbnailSize = remember { mutableStateOf(IntSize.Zero) }
    val thumbnailWidth = dimensionResource(id = R.dimen.posterWidth)
    val thumbnailHeight = thumbnailWidth * posterAspectRatio
    val imageWidth = thumbnailWidth * 1f
    val imageHeight = thumbnailHeight * parallaxFactor
    val thumbnailPainter = movie.thumbnailPainter(context = context, size = thumbnailSize.value)

    val textTheme = MaterialTheme.typography
    val cornerRadius = dimensionResource(id = R.dimen.cornerRadius)

    Card(
        modifier = modifier
            .wrapContentHeight()
            .clickable { onMovieClicked(movie) },
        shape = RoundedCornerShape(size = cornerRadius)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            Image(
                painter = thumbnailPainter,
                contentDescription = "poster",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(imageWidth)
                    .height(imageHeight)
                    .clip(RoundedCornerShape(size = cornerRadius))
                    .onSizeChanged { size -> thumbnailSize.value = size }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            ) {
                Text(
                    text = movie.title,
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
    MaterialTheme {
        MovieListTile(movie = movie550) {}
    }
}