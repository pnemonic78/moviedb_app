import { createSlice } from '@reduxjs/toolkit'
import MoviesAction from './MoviesAction'
import { default as MoviesReducerState, default as MoviesState } from './MoviesState'

const initialState: MoviesReducerState = {
    loading: false,
    moviesNowPlaying: [],
    moviesPopular: [],
    moviesTopRated: [],
    moviesUpcoming: [],
}

export const moviesSlice = createSlice({
    name: 'movies',
    initialState,
    reducers: {
        nowPlaying: (state: MoviesState, action: MoviesAction) => {
            state.loading = false
            state.moviesNowPlaying = action.payload
        },
        popular: (state: MoviesState, action: MoviesAction) => {
            state.loading = false
            state.moviesPopular = action.payload
        },
        topRated: (state: MoviesState, action: MoviesAction) => {
            state.loading = false
            state.moviesTopRated = action.payload
        },
        upcoming: (state: MoviesState, action: MoviesAction) => {
            state.loading = false
            state.moviesUpcoming = action.payload
        }
    }
})

export const { nowPlaying, popular, topRated, upcoming } = moviesSlice.actions

export default moviesSlice.reducer
