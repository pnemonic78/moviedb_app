import Movie from './model/Movie'
import { MovieDetails } from './model/MovieDetails'
import { MoviesResponse } from './MoviesResponse'

export default abstract class TMBDApi {
    static api_url = "https://api.themoviedb.org/3/"
    static _image_url = "https://image.tmdb.org/t/p/{size}{path}"
    static _youtube_url = "https://www.youtube.com/watch?v={id}"
    static _youtube_thumbnail_url = "https://img.youtube.com/vi/{id}/0.jpg"
    static _facebook_url = "https://facebook.com/{id}"
    static _imdb_url = "https://imdb.com/name/{id}"
    static _instagram_url = "https://instagram.com/{id}"
    static _twitter_url = "https://twitter.com/{id}"

    private static _original = "original"
    private static _backdrop_sizes = [
        "w300",
        "w780",
        "w1280",
        TMBDApi._original,
    ]
    // private static _logo_sizes = [
    //   "w45",
    //   "w92",
    //   "w154",
    //   "w185",
    //   "w300",
    //   "w500",
    //   TMBDApi._original,
    // ]
    private static _poster_sizes = [
        "w92",
        "w154",
        "w185",
        "w342",
        "w500",
        "w780",
        TMBDApi._original,
    ]
    private static _profile_sizes = [
        "w45",
        "w185",
        "h632",
        TMBDApi._original,
    ]
    // private static _still_sizes = [
    //   "w92",
    //   "w185",
    //   "w300",
    //   _original,
    // ]

    static generatePosterUrl(path: string | null, width: number, height: number): string {
        if ((path == null) || (width <= 0) || (height <= 0)) {
            return ""
        }
        let size = TMBDApi.findSize(width, height, TMBDApi._poster_sizes)

        return TMBDApi._image_url.replace("{size}", size).replace("{path}", path)
    }

    static generateBackdropUrl(path: string | null, width: number, height: number): string {
        if ((path == null) || (width <= 0) || (height <= 0)) {
            return ""
        }
        let size = TMBDApi.findSize(width, height, TMBDApi._backdrop_sizes)

        return TMBDApi._image_url.replace("{size}", size).replace("{path}", path)
    }

    static generateProfileThumbnail(path: string | null, width: number, height: number): string {
        if ((path == null) || (width <= 0) || (height <= 0)) {
            return ""
        }
        let size = TMBDApi.findSize(width, height, TMBDApi._profile_sizes)

        return TMBDApi._image_url.replace("{size}", size).replace("{path}", path)
    }

    static findSize(width: number, height: number, sizes: string[]): string {
        let widthPx = width * 1.0
        let heightPx = height * 1.0

        var result = TMBDApi._original
        var minDelta = Number.MAX_VALUE
        var delta

        for (let size of sizes) {
            if (size.startsWith("w")) {
                var sizeWidth = parseFloat(size.substring(1))
                delta = Math.abs(widthPx - sizeWidth)
                if (delta < minDelta) {
                    minDelta = delta
                    result = size
                }
            } else if (size.startsWith("h")) {
                var sizeHeight = parseFloat(size.substring(1))
                delta = Math.abs(heightPx - sizeHeight)
                if (delta < minDelta) {
                    minDelta = delta
                    result = size
                }
            }
        }

        return result
    }

    abstract getNowPlaying(): Promise<MoviesResponse>

    abstract getPopular(): Promise<MoviesResponse>

    abstract getTopRated(): Promise<MoviesResponse>

    abstract getUpcoming(): Promise<MoviesResponse>

    abstract getMovieDetailsById(movieId: number): Promise<MovieDetails>

    async getMovieDetails(movie: Movie): Promise<MovieDetails> {
        return this.getMovieDetailsById(movie.id)
    }
}