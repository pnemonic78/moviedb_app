import React from 'react';
import { Image, StyleSheet, Text, View } from 'react-native';
import MovieGridTile from './MovieGridTile';
import TMBDApi from '../../tmdb_api/TMDBApi';

export default class MovieAllTile extends MovieGridTile {
    constructor(props) {
        super(props);
    }

    render() {
        let movie = this.props.movie;
        let styles = this.styles;
        let api = TMBDApi;

        let imageWidth = styles.thumbnail.width;
        let imageHeight = styles.thumbnail.height;
        let thumbnailUrl = api.generatePosterUrl(movie.poster_path, imageWidth, imageHeight);
        let thumbnailWidget = <Image source={{ uri: thumbnailUrl }} style={styles.thumbnail}></Image>;

        let titleWidget = <Text style={styles.title}>{movie.title}</Text>;

        let dateWidget = <Text style={styles.date}>{movie.release_date}</Text>

        return <View style={styles.tile}>
            {thumbnailWidget}
            {titleWidget}
            {dateWidget}
        </View>;
    }
}
