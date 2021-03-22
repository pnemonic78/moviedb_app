import { Action } from "redux";
import { MovieDetails } from "../../tmdb_api/model/MovieDetails";
import { MoviesActionType } from "./ActionTypes";

export interface MovieDetailsAction extends Action<MoviesActionType> {
    payload: MovieDetails | null,
}

export function fetchedMovieDetails(movie: MovieDetails): MovieDetailsAction {
    return {
        type: MoviesActionType.DetailsResponse,
        payload: movie,
    }
}
