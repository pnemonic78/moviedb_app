import React, { Component, ReactElement } from 'react';
import { FlatList, ListRenderItemInfo, StyleSheet } from 'react-native';
import { Movie } from '../../tmdb_api/model/Movie';
import { MovieAllTile } from './MovieAllTile';

const styleSheet = StyleSheet.create({
    slider: {
    },
});

interface MoviesSliderProps {
    movies: Movie[];
    onPress?: null | ((movie: Movie) => void);
}

export class MoviesSlider extends Component<MoviesSliderProps> {
    constructor(props: MoviesSliderProps) {
        super(props);
    }

    private renderItem(info: ListRenderItemInfo<Movie>): ReactElement<Movie> {
        return <MovieAllTile movie={info.item} onPress={this.props.onPress} />;
    }

    render() {
        let movies = this.props.movies;

        return <FlatList
            style={styleSheet.slider}
            data={movies}
            extraData={this}
            horizontal={true}
            renderItem={this.renderItem.bind(this)}
            keyExtractor={(item, i) => item.id.toString()}
            />;
    }
}
