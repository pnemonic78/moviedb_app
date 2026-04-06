import { Movie } from "@/tmdb_api/model/Movie";

export default interface MoviesState {
    loading: boolean
    moviesNowPlaying: Movie[]
    moviesPopular: Movie[]
    moviesTopRated: Movie[]
    moviesUpcoming: Movie[]
    showAsList: boolean
}
