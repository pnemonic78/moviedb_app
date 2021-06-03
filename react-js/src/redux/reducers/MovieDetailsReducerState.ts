import { MovieDetails } from "../../tmdb_api/model/MovieDetails";

export default interface MovieDetailsReducerState {
    loading: boolean
    movies: Map<number, MovieDetails>
}
