import React, { Component } from 'react';
import { GestureResponderEvent, ImageStyle, Pressable, StyleSheet, Text } from 'react-native';
import { Card } from 'react-native-elements';
import TMBDApi from '../../tmdb_api/TMDBApi';
import R from '../../res/R';
import { LoadingImage } from "../LoadingImage";
import { Movie } from '../../tmdb_api/model/Movie';

const posterGridWidth = 150.0;
const posterGridHeight = posterGridWidth * 1.5;

const styleSheet = StyleSheet.create({
    thumbnail: {
        borderTopLeftRadius: 20,
        borderTopRightRadius: 20,
        height: posterGridHeight,
        width: posterGridWidth,
    },
    tile: {
        borderRadius: 20,
        marginStart: 0,
        paddingBottom: 0,
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

interface MovieAllTileProps {
    movie: Movie;
    onPress?: null | ((movie: Movie) => void);
}

export class MovieAllTile extends Component<MovieAllTileProps> {
    constructor(props: MovieAllTileProps) {
        super(props);
        this.styles = styleSheet;
    }

    private styles: StyleSheet.NamedStyles<any>;

    private onPress(event: GestureResponderEvent) {
        let movie = this.props.movie;
        this.props.onPress?.(movie);
    }

    render() {
        let movie = this.props.movie;
        let styles = this.styles;
        let api = TMBDApi;

        let imageWidth = styles.thumbnail.width as number;
        let imageHeight = styles.thumbnail.height as number;
        let thumbnailUrl = api.generatePosterUrl(movie.poster_path, imageWidth, imageHeight);
        let thumbnailWidget = <LoadingImage
            defaultSource={R.images.outline_image}
            source={{ uri: thumbnailUrl }}
            style={styles.thumbnail as ImageStyle} />;

        let titleWidget = <Text style={styles.title} numberOfLines={2}>{movie.title + "\n"}</Text>;

        return <Pressable onPress={this.onPress?.bind(this)}>
            <Card containerStyle={styles.tile}>
                {thumbnailWidget}
                {titleWidget}
            </Card>
        </Pressable>;
    }
}