import { Action } from "redux";
import { Movie } from "../../tmdb_api/model/Movie";
import { MoviesActionType } from "./ActionTypes";

export interface MoviesAction extends Action<MoviesActionType> {
    payload: any,
}

export function fetchedMoviesNowPlaying(movies: Movie[]): MoviesAction {
    return {
        type: MoviesActionType.NowPlayingResponse,
        payload: movies,
    }
}

export function fetchedMoviesPopular(movies: Movie[]): MoviesAction {
    return {
        type: MoviesActionType.PopularResponse,
        payload: movies,
    }
}

export function fetchedMoviesTopRated(movies: Movie[]): MoviesAction {
    return {
        type: MoviesActionType.TopRatedResponse,
        payload: movies,
    }
}

export function fetchedMoviesUpcoming(movies: Movie[]): MoviesAction {
    return {
        type: MoviesActionType.UpcomingResponse,
        payload: movies,
    }
}
