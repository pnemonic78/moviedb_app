import { Movie } from './model/Movie'
import { MovieDetails } from './model/MovieDetails'
import { MoviesResponse } from './model/MoviesResponse'
import { Person } from './model/Person'

export default abstract class TMDBApi {
    static api_url = "https://api.themoviedb.org/3/"
    private static _image_url = "https://image.tmdb.org/t/p/{size}{path}"
    private static _youtube_url = "https://www.youtube.com/watch?v={id}"
    private static _youtube_thumbnail_url = "https://img.youtube.com/vi/{id}/0.jpg"
    private static _facebook_url = "https://facebook.com/{id}"
    private static _imdb_movie_url = "https://imdb.com/title/{id}"
    private static _imdb_person_url = "https://imdb.com/name/{id}"
    private static _instagram_url = "https://instagram.com/{id}"
    private static _twitter_url = "https://twitter.com/{id}"

    private static _original = "original"
    private static _backdrop_sizes = [
        "w300",
        "w780",
        "w1280",
        TMDBApi._original,
    ]
    // private static _logo_sizes = [
    //   "w45",
    //   "w92",
    //   "w154",
    //   "w185",
    //   "w300",
    //   "w500",
    //   TMDBApi._original,
    // ]
    private static _poster_sizes = [
        "w92",
        "w154",
        "w185",
        "w342",
        "w500",
        "w780",
        TMDBApi._original,
    ]
    private static _profile_sizes = [
        "w45",
        "w185",
        "h632",
        TMDBApi._original,
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
        let size = TMDBApi.findSize(width, height, TMDBApi._poster_sizes)

        return TMDBApi._image_url.replace("{size}", size).replace("{path}", path)
    }

    static generateBackdropUrl(path: string | null, width: number, height: number): string {
        if ((path == null) || (width <= 0) || (height <= 0)) {
            return ""
        }
        let size = TMDBApi.findSize(width, height, TMDBApi._backdrop_sizes)

        return TMDBApi._image_url.replace("{size}", size).replace("{path}", path)
    }

    static generateProfileThumbnail(path: string | null, width: number, height: number): string {
        if ((path == null) || (width <= 0) || (height <= 0)) {
            return ""
        }
        let size = TMDBApi.findSize(width, height, TMDBApi._profile_sizes)

        return TMDBApi._image_url.replace("{size}", size).replace("{path}", path)
    }

    static findSize(width: number, height: number, sizes: string[]): string {
        let widthPx = width * 1.0
        let heightPx = height * 1.0

        var result = TMDBApi._original
        var minDelta = 10000

        for (let size of sizes) {
            if (size.startsWith("w")) {
                let sizeWidth = parseFloat(size.substring(1))
                let delta = Math.abs(widthPx - sizeWidth)
                if (delta < minDelta) {
                    minDelta = delta
                    result = size
                }
            } else if (size.startsWith("h")) {
                let sizeHeight = parseFloat(size.substring(1))
                let delta = Math.abs(heightPx - sizeHeight)
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

    abstract getPersonById(personId: number): Promise<Person>

    async getPerson(person: Person): Promise<Person> {
        return this.getPersonById(person.id)
    }

    static generateFacebookUrl(id: string | undefined): string {
        return (id) ? TMDBApi._facebook_url.replace("{id}", id) : ""
    }

    static generateImdbUrl(id: string | undefined): string {
        if (id) {
            if (id.startsWith("nm")) {
                return TMDBApi._imdb_person_url.replace("{id}", id)
            }
            if (id.startsWith("tt")) {
                return TMDBApi._imdb_movie_url.replace("{id}", id)
            }
        }
        return "";
    }

    static generateInstagramUrl(id: string | undefined): string {
        return (id) ? TMDBApi._instagram_url.replace("{id}", id) : ""
    }

    static generateTwitterUrl(id: string | undefined): string {
        return (id) ? TMDBApi._twitter_url.replace("{id}", id) : ""
    }
}