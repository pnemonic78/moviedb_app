import movieDetailsReducer from "@/features/movies/redux/MovieDetailsReducer"
import moviesReducer from "@/features/movies/redux/MoviesReducer"
import personDetailsReducer from "@/features/people/redux/PersonDetailsReducer"
import { combineReducers } from "@reduxjs/toolkit"

export const appReducers = combineReducers({
    movies: moviesReducer,
    movie: movieDetailsReducer,
    person: personDetailsReducer,
})
