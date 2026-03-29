import { MovieDetails } from '@/tmdb_api/model/MovieDetails'
import { createSlice } from '@reduxjs/toolkit'
import MovieDetailsAction from './MovieDetailsAction'
import MovieDetailsState from './MovieDetailsState'

const initialState: MovieDetailsState = {
    loading: false,
    movies: new Map(),
}

export const movieSlice = createSlice({
    name: 'movie',
    initialState,
    reducers: {
        details: (state: MovieDetailsState, action: MovieDetailsAction) => {
            state.loading = false
            let movie = action.payload
            if (movie != null) {
                let movies = state.movies ?? new Map<number, MovieDetails>()
                movies.set(movie.id, movie)
                state.movies = movies
            }
        }
    }
})

export const { details } = movieSlice.actions

export default movieSlice.reducer
