import React, { Component } from 'react';
import { Image, StyleSheet, Text, View } from 'react-native';
import { TMBDApi } from './../../tmdb_api/TMDBApi';

const posterGridWidth = 150.0;
const posterGridHeight = posterGridWidth * 1.5;

const stylesMovieGridTile = StyleSheet.create({
    date: {
        backgroundColor: 'blue',
        padding: 8,
    },
    thumbnail: {
        backgroundColor: 'cyan',
        height: posterGridHeight,
        width: posterGridWidth,
    },
    tile: {
        backgroundColor: 'yellow',
        width: posterGridWidth,
    },
    title: {
        backgroundColor: 'green',
        color: 'black',
        padding: 8,
    },
});

export default class MovieGridTile extends Component {
    constructor(props) {
        super(props);
        this.styles = stylesMovieGridTile;
    }

    render() {
        let movie = this.props.movie;
        let styles = this.styles;

        let thumbnailWidget = <Image style={styles.thumbnail}></Image>;
        let titleWidget = <Text style={styles.title}>{movie.title}</Text>;
        let dateWidget = <Text style={styles.date}>{movie.release_date}</Text>

        return <View style={styles.tile}>
            {thumbnailWidget}
            {titleWidget}
            {dateWidget}
        </View>;
    }
}
