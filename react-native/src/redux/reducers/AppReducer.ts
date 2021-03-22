import { combineReducers, createStore } from "redux"
import MoviesReducerState from "./MoviesReducerState"
import MovieDetailsReducerState from "./MovieDetailsReducerState"
import { moviesReducer } from './MoviesReducer'
import { movieDetailsReducer } from './MovieDetailsReducer'

export interface AppReducersState {
    moviesReducer: MoviesReducerState,
    movieDetailsReducer: MovieDetailsReducerState,
}

const appReducers = combineReducers({
    moviesReducer,
    movieDetailsReducer,
})

const store = createStore(appReducers)

export default store
