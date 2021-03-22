import { Reducer } from 'redux'
import { MoviesActionType } from '../actions/ActionTypes'
import { MoviesAction } from '../actions/MoviesAction'
import MoviesReducerState from './MoviesReducerState'

const initialState: MoviesReducerState = {
    loading: false,
    moviesNowPlaying: [],
    moviesPopular: [],
    moviesTopRated: [],
    moviesUpcoming: [],
}

const defaultAction: MoviesAction = {
    type: MoviesActionType.None,
    payload: null,
}

export const moviesReducer: Reducer<MoviesReducerState, MoviesAction> = (
    state: MoviesReducerState = initialState,
    action: MoviesAction = defaultAction
): MoviesReducerState => {
    switch (action.type) {
        case MoviesActionType.MovieError:
            return state
        case MoviesActionType.NowPlayingResponse:
            return { ...state, moviesNowPlaying: action.payload }
        case MoviesActionType.PopularResponse:
            return { ...state, moviesPopular: action.payload }
        case MoviesActionType.TopRatedResponse:
            return { ...state, moviesTopRated: action.payload }
        case MoviesActionType.UpcomingResponse:
            return { ...state, moviesUpcoming: action.payload }
        default:
            return state
    }
}
