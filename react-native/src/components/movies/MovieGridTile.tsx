import React, { Component } from 'react';
import { GestureResponderEvent, ImageStyle, Pressable, StyleSheet, Text, TextStyle } from 'react-native';
import { Card, Rating } from 'react-native-elements';
import TMBDApi from '../../tmdb_api/TMDBApi';
import R from '../../res/R';
import { LoadingImage } from "../LoadingImage";
import { Movie } from '../../tmdb_api/model/Movie';
import { OnMoviePress } from './MovieClickListener';

const posterGridWidth = 150.0;
const posterGridHeight = posterGridWidth * 1.5;

const styleSheet = StyleSheet.create({
    date: {
        paddingStart: 8,
        paddingEnd: 8,
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
        paddingBottom: 8,
        paddingLeft: 0,
        paddingRight: 0,
        paddingTop: 0,
    },
    title: {
        fontSize: 18,
        fontWeight: "bold",
        paddingStart: 8,
        paddingEnd: 8,
        width: posterGridWidth,
    },
    vote: {
        paddingStart: 8,
        paddingEnd: 8,
        paddingTop: 4,
        width: posterGridWidth,
    },
});

interface MovieGridTileProps {
    movie: Movie;
    onMoviePress?: OnMoviePress;
}

export class MovieGridTile extends Component<MovieGridTileProps> {
    constructor(props: MovieGridTileProps) {
        super(props);
        this.styles = styleSheet;
    }

    private styles: StyleSheet.NamedStyles<any>;

    private onPress(event: GestureResponderEvent) {
        let movie = this.props.movie;
        this.props.onMoviePress?.(movie);
    }

    render() {
        let movie = this.props.movie;
        let styles = this.styles;

        let imageWidth = styles.thumbnail.width as number;
        let imageHeight = styles.thumbnail.height as number;
        let thumbnailUrl = TMBDApi.generatePosterUrl(movie.poster_path, imageWidth, imageHeight);
        let thumbnailWidget = <LoadingImage
            defaultSource={R.images.outline_image}
            source={{ uri: thumbnailUrl }}
            style={styles.thumbnail as ImageStyle} />;

        let voteAverageWidget = <Rating
            type="custom"
            ratingCount={5}
            imageSize={20}
            readonly={true}
            ratingColor={"#FFEB3B"}
            startingValue={movie.vote_average / 2.0}
            style={styles.vote} />;

        let titleWidget = <Text style={styles.title} numberOfLines={2}>{movie.title + "\n"}</Text>;

        let dateWidget = <Text style={styles.date}>{movie.release_date}</Text>;

        return <Pressable onPress={this.onPress?.bind(this)}>
            <Card containerStyle={styles.tile}>
                {thumbnailWidget}
                {voteAverageWidget}
                {titleWidget}
                {dateWidget}
            </Card>
        </Pressable>;
    }
}