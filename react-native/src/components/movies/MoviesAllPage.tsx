import React, { Component } from 'react'
import { ScrollView, StyleSheet } from 'react-native'
import MoviesAllSection from './MoviesAllSection'
import { MoviesSlider } from "./MoviesSlider"
import R from '../../res/R'
import TMDBApiImpl from '../../tmdb_api/TMDBApiImpl'
import TMDBApi from '../../tmdb_api/TMDBApi'
import { Movie } from '../../tmdb_api/model/Movie'
import { StackScreenProps } from '@react-navigation/stack'
import { ScreenName } from '../main/ScreenName'
import { fetchedMoviesNowPlaying, fetchedMoviesPopular, fetchedMoviesTopRated, fetchedMoviesUpcoming, MoviesAction } from '../../redux/actions/MoviesAction'
import MoviesReducerState from '../../redux/reducers/MoviesReducerState'
import { connect, DispatchProp } from 'react-redux'
import MoviesReducerProps from '../../redux/reducers/MoviesReducerProps'
import { AppReducersState } from '../../redux/reducers/AppReducer'

const styleSheet = StyleSheet.create({
    scroller: {
    },
    scrollerContainer: {
        padding: 8,
    },
})

interface MoviesAllPageProps extends StackScreenProps<any>, DispatchProp<MoviesAction> {
    moviesNowPlaying: Movie[],
    moviesPopular: Movie[],
    moviesTopRated: Movie[],
    moviesUpcoming: Movie[],
}

interface MoviesAllPageState {
}

export class MoviesAllPageComponent extends Component<MoviesAllPageProps, MoviesAllPageState> {
    constructor(props: MoviesAllPageProps) {
        super(props)
    }

    private api: TMDBApi = new TMDBApiImpl()

    private getMoviesNowPlaying(): Movie[] {
        var movies = this.props.moviesNowPlaying
        if (!movies?.length) {
            let dispatch = this.props.dispatch;
            this.api.getNowPlaying()
                .then(data => dispatch(fetchedMoviesNowPlaying(data.results)))
        }
        return movies
    }

    private getMoviesPopular(): Movie[] {
        var movies = this.props.moviesPopular
        if (!movies?.length) {
            let dispatch = this.props.dispatch;
            this.api.getPopular()
                .then(data => dispatch(fetchedMoviesPopular(data.results)))
        }
        return movies
    }

    private getMoviesTopRated(): Movie[] {
        var movies = this.props.moviesTopRated
        if (!movies?.length) {
            let dispatch = this.props.dispatch;
            this.api.getTopRated()
                .then(data => dispatch(fetchedMoviesTopRated(data.results)))
        }
        return movies
    }

    private getMoviesUpcoming(): Movie[] {
        var movies = this.props.moviesUpcoming
        if (!movies?.length) {
            let dispatch = this.props.dispatch;
            this.api.getUpcoming()
                .then(data => dispatch(fetchedMoviesUpcoming(data.results)))
        }
        return movies
    }

    /// Navigates to the movies page.
    private navigateToPage(pageId: string, movies: Movie[]) {
        let navigation = this.props.navigation
        navigation.navigate(pageId, { movies })
    }

    /// Navigates to the movie details.
    private navigateToMovie(movie: Movie) {
        let navigation = this.props.navigation
        navigation.navigate(ScreenName.MOVIE_DETAILS, { movie })
    }

    private onTapNowPlaying() {
        let movies = this.getMoviesNowPlaying()
        this.navigateToPage(ScreenName.MOVIES_NOW_PLAYING, movies)
    }

    private onTapPopular() {
        let movies = this.getMoviesPopular()
        this.navigateToPage(ScreenName.MOVIES_POPULAR, movies)
    }

    private onTapTopRated() {
        let movies = this.getMoviesTopRated()
        this.navigateToPage(ScreenName.MOVIES_TOP_RATED, movies)
    }

    private onTapUpcoming() {
        let movies = this.getMoviesUpcoming()
        this.navigateToPage(ScreenName.MOVIES_UPCOMING, movies)
    }

    private onTapMovie(movie: Movie) {
        this.navigateToMovie(movie)
    }

    render() {
        return (
            <ScrollView
                style={styleSheet.scroller}
                contentContainerStyle={styleSheet.scrollerContainer}>
                <MoviesAllSection
                    label={R.string.popular}
                    onPress={this.onTapPopular.bind(this)} />
                <MoviesSlider
                    movies={this.getMoviesPopular()}
                    onMoviePress={this.onTapMovie.bind(this)} />
                <MoviesAllSection
                    label={R.string.now_playing}
                    onPress={this.onTapNowPlaying.bind(this)} />
                <MoviesSlider
                    movies={this.getMoviesNowPlaying()}
                    onMoviePress={this.onTapMovie.bind(this)} />
                <MoviesAllSection
                    label={R.string.upcoming}
                    onPress={this.onTapUpcoming.bind(this)} />
                <MoviesSlider
                    movies={this.getMoviesUpcoming()}
                    onMoviePress={this.onTapMovie.bind(this)} />
                <MoviesAllSection
                    label={R.string.top_rated}
                    onPress={this.onTapTopRated.bind(this)} />
                <MoviesSlider
                    movies={this.getMoviesTopRated()}
                    onMoviePress={this.onTapMovie.bind(this)} />
            </ScrollView>
        )
    }
}

function mapStateToProps(state: AppReducersState): object {
    const stateReducer = state.moviesReducer;
    return {
        moviesNowPlaying: stateReducer.moviesNowPlaying,
        moviesPopular: stateReducer.moviesPopular,
        moviesTopRated: stateReducer.moviesTopRated,
        moviesUpcoming: stateReducer.moviesUpcoming,
    }
}

const MoviesAllPage = connect(mapStateToProps)(MoviesAllPageComponent)
export default MoviesAllPage
