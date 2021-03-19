import React, { Component } from 'react';
import { GestureResponderEvent, ImageStyle, Pressable, StyleSheet, Text, TextStyle } from 'react-native';
import { Card, Rating } from 'react-native-elements';
import TMBDApi from '../../tmdb_api/TMDBApi';
import R from '../../res/R';
import { LoadingImage } from "../LoadingImage";
import { Movie } from '../../tmdb_api/model/Movie';
import { OnMoviePress } from './MovieClickListener';
import { Utils } from '../main/Utils';

const posterGridWidth = R.dimen.posterGridWidth;
const posterGridRatio = R.dimen.posterGridRatio;

const styleSheet = StyleSheet.create({
    date: {
        paddingStart: 8,
        paddingEnd: 8,
        paddingTop: 4,
    },
    thumbnail: {
        borderTopLeftRadius: R.dimen.cardRadius,
        borderTopRightRadius: R.dimen.cardRadius,
        height: posterGridWidth * posterGridRatio,
        width: posterGridWidth,
    },
    tile: {
        borderRadius: R.dimen.cardRadius,
        paddingLeft: 0,
        paddingRight: 0,
        paddingTop: 0,
    },
    title: {
        fontSize: 18,
        fontWeight: "bold",
        paddingStart: 8,
        paddingEnd: 8,
        paddingTop: 4,
        width: posterGridWidth,
    },
    vote: {
        paddingStart: 8,
        paddingEnd: 8,
        paddingTop: 8,
        width: posterGridWidth,
    },
});

interface MovieGridTileProps {
    movie: Movie;
    onMoviePress?: OnMoviePress;
    width?: number | null;
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

        let imageWidth = this.props.width ?? (styles.thumbnail.width as number);
        let imageHeight = (styles.thumbnail.height as number) * posterGridRatio;
        let thumbnailUrl = TMBDApi.generatePosterUrl(movie.poster_path, imageWidth, imageHeight);
        let thumbnailWidget = <LoadingImage
            defaultSource={R.drawable.outline_image}
            source={{ uri: thumbnailUrl }}
            style={styles.thumbnail as ImageStyle}
            width={imageWidth}
            height={imageHeight} />;

        let voteAverageWidget = <Rating
            type="custom"
            ratingCount={5}
            imageSize={20}
            readonly={true}
            ratingColor={R.color.ratingColor}
            startingValue={movie.vote_average / 2.0}
            style={styles.vote} />;

        let titleWidget = <Text style={styles.title} numberOfLines={2}>{movie.title + "\n"}</Text>;

        let dateValue = Utils.formatDate(movie.release_date);
        let dateWidget = <Text style={styles.date}>{dateValue}</Text>;

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