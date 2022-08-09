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

    private const val IMAGE_URL = "https://image.tmdb.org/t/p/%s%s"

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

        return String.format(Locale.US, IMAGE_URL, size, path)
    }

    fun findSize(width: Int, height: Int, sizes: Array<String>): String {
        for (size in sizes) {
            if (size.startsWith("w")) {
                var posterWidth = size.substring(1).toInt()
                if (width <= (posterWidth + (posterWidth shr 2))) {
                    return size
                }
            } else if (size.startsWith("h")) {
                var posterHeight = size.substring(1).toInt()
                if (height <= (posterHeight + (posterHeight shr 2))) {
                    return size
                }
            }
        }
        return "original"
    }
}
