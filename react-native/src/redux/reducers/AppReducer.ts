import { combineReducers, createStore } from "redux"
import { moviesReducer } from './MoviesReducer'
import MoviesReducerState from "./MoviesReducerState"

export interface AppReducersState {
    moviesReducer: MoviesReducerState,
}

const appReducers = combineReducers({
    moviesReducer,
})

const store = createStore(appReducers)

export default store
