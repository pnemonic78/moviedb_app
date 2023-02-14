package com.tikal.tmdb.api

import android.content.Context
import android.content.res.Resources
import android.widget.ImageView
import com.tikal.tmdb.json.model.Movie
import com.tikal.tmdb.ui.common.R
import java.util.Locale

/**
 * TMDB API.
 */
object TmdbApi {

    private const val _image_url = "https://image.tmdb.org/t/p/%s%s"
    private const val _youtube_url = "https://www.youtube.com/watch?v=%s"
    private const val _youtube_thumbnail_url = "https://img.youtube.com/vi/%s/0.jpg"
    private const val _facebook_url = "https://facebook.com/%s"
    private const val _imdb_person_url = "https://imdb.com/name/%s"
    private const val _imdb_title_url = "https://imdb.com/title/%s"
    private const val _instagram_url = "https://instagram.com/%s"
    private const val _twitter_url = "https://twitter.com/%s"

    private var posterSizes: Array<String> = emptyArray()

    fun generatePosterUrl(movie: Movie, target: ImageView): String? {
        return generatePosterUrl(movie.posterPath, target)
    }

    fun generatePosterUrl(path: String?, target: ImageView): String? {
        var targetWidth = target.measuredWidth
        var targetHeight = target.measuredHeight
        val lp = target.layoutParams
        if (targetWidth <= 0) {
            targetWidth = lp.width
        }
        if (targetHeight <= 0) {
            targetHeight = lp.height
        }
        return generatePosterUrl(target.context, path, targetWidth, targetHeight)
    }

    fun generatePosterUrl(context: Context, path: String?, width: Int, height: Int): String? {
        return generatePosterUrl(context.resources, path, width, height)
    }

    fun generatePosterUrl(resources: Resources, path: String?, width: Int, height: Int): String? {
        if ((path.isNullOrEmpty()) || (width <= 0) || (height <= 0)) {
            return null
        }
        if (posterSizes.isEmpty()) {
            posterSizes = resources.getStringArray(R.array.poster_sizes)
        }
        val size = findSize(width, height, posterSizes)

        return String.format(Locale.US, _image_url, size, path)
    }

    private fun findSize(width: Int, height: Int, sizes: Array<String>): String {
        for (size in sizes) {
            if (size.startsWith("w")) {
                val posterWidth = size.substring(1).toInt()
                if (width <= (posterWidth + (posterWidth shr 2))) {
                    return size
                }
            } else if (size.startsWith("h")) {
                val posterHeight = size.substring(1).toInt()
                if (height <= (posterHeight + (posterHeight shr 2))) {
                    return size
                }
            }
        }
        return "original"
    }

    fun generateFacebookUrl(id: String): String {
        return String.format(_facebook_url, id)
    }

    fun generateImdbPersonUrl(id: String): String {
        return String.format(_imdb_person_url, id)
    }

    fun generateImdbMovieUrl(id: String): String {
        return String.format(_imdb_title_url, id)
    }

    fun generateInstagramUrl(id: String): String {
        return String.format(_instagram_url, id)
    }

    fun generateTwitterUrl(id: String): String {
        return String.format(_twitter_url, id)
    }
}
