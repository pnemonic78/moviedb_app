import { combineReducers, createStore } from "redux"
import MoviesReducerState from "./MoviesReducerState"
import MovieDetailsReducerState from "./MovieDetailsReducerState"
import { moviesReducer } from './MoviesReducer'
import { movieDetailsReducer } from './MovieDetailsReducer'
import { personDetailsReducer } from './PersonDetailsReducer'
import PersonDetailsReducerState from "./PersonDetailsReducerState"

export interface AppReducersState {
    moviesReducer: MoviesReducerState,
    movieDetailsReducer: MovieDetailsReducerState,
    personDetailsReducer: PersonDetailsReducerState,
}

const appReducers = combineReducers({
    moviesReducer,
    movieDetailsReducer,
    personDetailsReducer,
})

const store = createStore(appReducers)

export default store
