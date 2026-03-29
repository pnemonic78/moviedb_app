import { MovieDetails } from "@/tmdb_api/model/MovieDetails";

export default interface MovieDetailsState {
    loading: boolean
    movies: Map<number, MovieDetails>
}
