package com.tmdbcodlab.android.api

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.model.Movie
import com.tmdbcodlab.android.model.MovieDetails
import java.util.*

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbApi {
    companion object {

        private val IMAGE_URL = "https://image.tmdb.org/t/p/%s%s"

        private var posterSizes: Array<String> = emptyArray()

        fun showPoster(movie: Movie, target: ImageView) {
            val path = generatePosterUrl(movie, target)
            if (!TextUtils.isEmpty(path)) {
                Glide.with(target.context)
                        .load(path)
                        .into(target)
            } else {
                target.setImageDrawable(null)
            }
        }

        fun showPoster(movie: MovieDetails, target: ImageView) {
            val path = generatePosterUrl(movie, target)
            if (!TextUtils.isEmpty(path)) {
                Glide.with(target.context)
                        .load(path)
                        .into(target)
            } else {
                target.setImageDrawable(null)
            }
        }

        fun generatePosterUrl(movie: Movie, target: ImageView): String? {
            return generatePosterUrl(movie.posterPath, target)
        }

        fun generatePosterUrl(movie: MovieDetails, target: ImageView): String? {
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
            if ((path == null) || (width <= 0) || (height <= 0)) {
                return null
            }
            if (posterSizes.isEmpty()) {
                posterSizes = context.resources.getStringArray(R.array.poster_sizes)
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

}