import React, { Component } from 'react';
import { ImageStyle, StyleSheet, Text, View } from 'react-native';
import Movie from '../../tmdb_api/model/Movie';
import LoadingImage from '../LoadingImage';

const posterGridWidth = 150.0;
const posterGridHeight = posterGridWidth * 1.5;

const styleSheet = StyleSheet.create({
    date: {
        padding: 8,
    },
    thumbnail: {
        borderTopLeftRadius: 20,
        borderTopRightRadius: 20,
        height: posterGridHeight,
        width: posterGridWidth,
    },
    tile: {
        borderRadius: 20,
        marginStart: 0,
        paddingLeft: 0,
        paddingRight: 0,
        paddingTop: 0,
    },
    title: {
        fontSize: 18,
        fontWeight: "bold",
        padding: 8,
        width: posterGridWidth,
    },
});

interface MovieGridTileProps {
    movie: Movie,
}

export default class MovieGridTile extends Component<MovieGridTileProps> {
    constructor(props: MovieGridTileProps) {
        super(props);
        this.styles = styleSheet;
    }

    private styles: StyleSheet.NamedStyles<any>;

    render() {
        let movie = this.props.movie;
        let styles = this.styles;

        let thumbnailWidget = <LoadingImage source={0} style={styles.thumbnail as ImageStyle}></LoadingImage>;
        let titleWidget = <Text style={styles.title}>{movie.title}</Text>;
        let dateWidget = <Text style={styles.date}>{movie.release_date}</Text>;

        return <View style={styles.tile}>
            {thumbnailWidget}
            {titleWidget}
            {dateWidget}
        </View>;
    }
}