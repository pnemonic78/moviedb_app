import React from 'react';
import { Text, View } from 'react-native';
import MovieGridTile from './MovieGridTile';
import TMBDApi from '../../tmdb_api/TMDBApi';
import R from '../../res/R';
import LoadingImage from "../LoadingImage";

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
        let thumbnailWidget = <LoadingImage
            defaultSource={R.images.outline_image}
            source={{ uri: thumbnailUrl }}
            style={styles.thumbnail} />;

        let titleWidget = <Text style={styles.title} numberOfLines={2}>{movie.title}</Text>;

        return <View style={styles.tile}>
            {thumbnailWidget}
            {titleWidget}
        </View>;
    }
}
