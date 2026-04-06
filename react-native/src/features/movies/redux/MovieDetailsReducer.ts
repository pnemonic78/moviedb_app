import { createSlice } from '@reduxjs/toolkit'
import MovieDetailsAction from './MovieDetailsAction'
import MovieDetailsState from './MovieDetailsState'

const initialState: MovieDetailsState = {
    loading: false,
    movies: [],
}

export const movieSlice = createSlice({
    name: 'movie',
    initialState,
    reducers: {
        details: (state: MovieDetailsState, action: MovieDetailsAction) => {
            state.loading = false
            let movie = action.payload
            if (movie != null) {
                let movies = state.movies ?? []
                let index = movies.findIndex((m) => m.id == movie.id)
                if (index >= 0) {
                    movies[index] = movie
                } else {
                    movies.concat(movie)
                }
                state.movies = movies
            }
        }
    }
})

export const { details } = movieSlice.actions

export default movieSlice.reducer
