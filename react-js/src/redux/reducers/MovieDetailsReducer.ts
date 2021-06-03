import { Reducer } from 'redux'
import { MovieDetails } from '../../tmdb_api/model/MovieDetails'
import { MoviesActionType } from '../actions/ActionTypes'
import { MovieDetailsAction } from '../actions/MovieDetailsAction'
import MovieDetailsReducerState from './MovieDetailsReducerState'

const initialState: MovieDetailsReducerState = {
    loading: false,
    movies: new Map(),
}

const defaultAction: MovieDetailsAction = {
    type: MoviesActionType.None,
    payload: null,
}

export const movieDetailsReducer: Reducer<MovieDetailsReducerState, MovieDetailsAction> = (
    state: MovieDetailsReducerState = initialState,
    action: MovieDetailsAction = defaultAction
): MovieDetailsReducerState => {
    switch (action.type) {
        case MoviesActionType.MovieError:
            return state
        case MoviesActionType.DetailsResponse:
            let movie = action.payload
            if (movie != null) {
                let movies = new Map<number, MovieDetails>(state.movies)
                movies.set(movie.id, movie)
                return { ...state, movies: movies }
            }
            return state
        default:
            return state
    }
}
