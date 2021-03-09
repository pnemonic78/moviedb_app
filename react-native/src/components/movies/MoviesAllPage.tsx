import React, { Component } from 'react';
import { ScrollView, StyleSheet } from 'react-native';
import MoviesAllSection from './MoviesAllSection';
import MoviesSlider from "./MoviesSlider";
import R from '../../res/R';
import TMDBApiImpl from '../../tmdb_api/TMDBApiImpl';
import TMDBApi from '../../tmdb_api/TMDBApi';
import Movie from '../../tmdb_api/model/Movie';

const styleSheet = StyleSheet.create({
    scroller: {
    },
    scrollerContainer: {
        padding: 8,
    },
});

interface MoviesAllPageProps {
}

interface MoviesAllPageState {
    moviesNowPlaying: Movie[],
    moviesPopular: Movie[],
    moviesTopRated: Movie[],
    moviesUpcoming: Movie[],
}

export default class MoviesAllPage extends Component<MoviesAllPageProps, MoviesAllPageState> {
    constructor(props: MoviesAllPageProps) {
        super(props);
        this.state = {
            moviesNowPlaying: [],
            moviesPopular: [],
            moviesTopRated: [],
            moviesUpcoming: [],
        };
    }

    private api: TMDBApi = new TMDBApiImpl();

    private getMoviesNowPlaying(): Movie[] {
        var movies = this.state.moviesNowPlaying;
        if (!movies?.length) {
            this.api.getNowPlaying()
                .then(data => this.setState({ moviesNowPlaying: data }));
        }
        return movies;
    }

    private getMoviesPopular(): Movie[] {
        var movies = this.state.moviesPopular;
        if (!movies?.length) {
            this.api.getPopular()
                .then(data => this.setState({ moviesPopular: data }));
        }
        return movies;
    }

    private getMoviesTopRated(): Movie[] {
        var movies = this.state.moviesTopRated;
        if (!movies?.length) {
            this.api.getTopRated()
                .then(data => this.setState({ moviesTopRated: data }));
        }
        return movies;
    }

    private getMoviesUpcoming(): Movie[] {
        var movies = this.state.moviesUpcoming;
        if (!movies?.length) {
            this.api.getUpcoming()
                .then(data => this.setState({ moviesUpcoming: data }));
        }
        return movies;
    }

    render() {
        return (
            <ScrollView style={styleSheet.scroller} contentContainerStyle={styleSheet.scrollerContainer}>
                <MoviesAllSection label={ R.strings.popular }/>
                <MoviesSlider movies={ this.getMoviesPopular() }/>
                <MoviesAllSection label={ R.strings.now_playing }/>
                <MoviesSlider movies={ this.getMoviesNowPlaying() }/>
                <MoviesAllSection label={R.strings.upcoming }/>
                <MoviesSlider movies={ this.getMoviesUpcoming() }/>
                <MoviesAllSection label={ R.strings.top_rated }/>
                <MoviesSlider movies={ this.getMoviesTopRated() } />
            </ScrollView>
        );
    }
}