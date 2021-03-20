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

const styleSheet = StyleSheet.create({
    scroller: {
    },
    scrollerContainer: {
        padding: 8,
    },
})

interface MoviesAllPageProps extends StackScreenProps<any> {
}

interface MoviesAllPageState {
    moviesNowPlaying: Movie[]
    moviesPopular: Movie[]
    moviesTopRated: Movie[]
    moviesUpcoming: Movie[]
}

export class MoviesAllPage extends Component<MoviesAllPageProps, MoviesAllPageState> {
    constructor(props: MoviesAllPageProps) {
        super(props)
        this.state = {
            moviesNowPlaying: [],
            moviesPopular: [],
            moviesTopRated: [],
            moviesUpcoming: [],
        }
    }

    private api: TMDBApi = new TMDBApiImpl()

    private getMoviesNowPlaying(): Movie[] {
        var movies = this.state.moviesNowPlaying
        if (!movies?.length) {
            this.api.getNowPlaying()
                .then(data => this.setState({ moviesNowPlaying: data.results }))
        }
        return movies
    }

    private getMoviesPopular(): Movie[] {
        var movies = this.state.moviesPopular
        if (!movies?.length) {
            this.api.getPopular()
                .then(data => this.setState({ moviesPopular: data.results }))
        }
        return movies
    }

    private getMoviesTopRated(): Movie[] {
        var movies = this.state.moviesTopRated
        if (!movies?.length) {
            this.api.getTopRated()
                .then(data => this.setState({ moviesTopRated: data.results }))
        }
        return movies
    }

    private getMoviesUpcoming(): Movie[] {
        var movies = this.state.moviesUpcoming
        if (!movies?.length) {
            this.api.getUpcoming()
                .then(data => this.setState({ moviesUpcoming: data.results }))
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
                    onMoviePress={this.navigateToMovie.bind(this)} />
                <MoviesAllSection
                    label={R.string.now_playing}
                    onPress={this.onTapNowPlaying.bind(this)} />
                <MoviesSlider
                    movies={this.getMoviesNowPlaying()}
                    onMoviePress={this.navigateToMovie.bind(this)} />
                <MoviesAllSection
                    label={R.string.upcoming}
                    onPress={this.onTapUpcoming.bind(this)} />
                <MoviesSlider
                    movies={this.getMoviesUpcoming()}
                    onMoviePress={this.navigateToMovie.bind(this)} />
                <MoviesAllSection
                    label={R.string.top_rated}
                    onPress={this.onTapTopRated.bind(this)} />
                <MoviesSlider
                    movies={this.getMoviesTopRated()}
                    onMoviePress={this.navigateToMovie.bind(this)} />
            </ScrollView>
        )
    }
}