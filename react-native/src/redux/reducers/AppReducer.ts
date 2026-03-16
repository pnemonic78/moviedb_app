import { combineReducers } from "@reduxjs/toolkit"
import { movieDetailsReducer } from './MovieDetailsReducer'
import MovieDetailsReducerState from "./MovieDetailsReducerState"
import { moviesReducer } from './MoviesReducer'
import MoviesReducerState from "./MoviesReducerState"
import { personDetailsReducer } from './PersonDetailsReducer'
import PersonDetailsReducerState from "./PersonDetailsReducerState"

export interface AppReducersState {
    moviesReducer: MoviesReducerState,
    movieDetailsReducer: MovieDetailsReducerState,
    personDetailsReducer: PersonDetailsReducerState,
}

export const appReducers = combineReducers({
    moviesReducer,
    movieDetailsReducer,
    personDetailsReducer,
})
