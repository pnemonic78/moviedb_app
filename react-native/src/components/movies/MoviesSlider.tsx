import React, { Component, ReactElement } from 'react';
import { FlatList, ListRenderItemInfo, StyleSheet } from 'react-native';
import Movie from '../../tmdb_api/model/Movie';
import MovieAllTile from './MovieAllTile';

const styleSheet = StyleSheet.create({
    slider: {
    },
});

interface MoviesSliderProps {
    movies: Movie[],
}

export default class MoviesSlider extends Component<MoviesSliderProps> {
    constructor(props: MoviesSliderProps) {
        super(props);
    }

    private renderItem(info: ListRenderItemInfo<Movie>): ReactElement<Movie> {
        return <MovieAllTile movie={info.item} />;
    }

    render() {
        let movies = this.props.movies;

        return <FlatList
            style={styleSheet.slider}
            data={movies}
            horizontal={true}
            renderItem={this.renderItem}
            keyExtractor={(item, i) => item.id.toString()}
            />;
    }
}
