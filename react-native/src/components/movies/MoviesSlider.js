import React, { Component } from 'react';
import { FlatList, StyleSheet, View } from 'react-native';
import MovieAllTile from './MovieAllTile';

const styles = StyleSheet.create({
    slider: {
    },
});

export default class MoviesSlider extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movies: require('./data.json').results,
        };
    }

    renderMovie({ item }) {
        return <MovieAllTile movie={item}/>;
    }

    render() {
        let movies = this.state.movies;

        return <FlatList
            style={styles.slider}
            data={movies}
            horizontal={true}
            renderItem={this.renderMovie}
            keyExtractor={(item, i) => item.id.toString()}
            />;
    }
}
