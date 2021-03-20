import React, { Component } from 'react';
import { GestureResponderEvent, ImageStyle, Pressable, StyleSheet, Text, View } from 'react-native';
import { Card, Rating } from 'react-native-elements';
import TMBDApi from '../../tmdb_api/TMDBApi';
import R from '../../res/R';
import { LoadingImage } from "../LoadingImage";
import { Movie, MovieClass } from '../../tmdb_api/model/Movie';
import { OnMoviePress } from './MovieClickListener';
import { Utils } from '../main/Utils';

const posterGridWidth = R.dimen.posterGridWidth;
const posterGridHeight = R.dimen.posterGridHeight;

const styleSheet = StyleSheet.create({
    column: {
        flex: 1,
        flexDirection: 'column',
    },
    date: {
        fontSize: 16,
        paddingTop: 8,
    },
    summary: {
        flex: 1,
        flexWrap: 'wrap',
        fontSize: 16,
        paddingTop: 8,
    },
    thumbnail: {
        borderRadius: R.dimen.cardRadius,
        marginEnd: 8,
        height: posterGridHeight,
        width: posterGridWidth,
    },
    tile: {
        borderRadius: R.dimen.cardRadius,
        padding: 8,
    },
    title: {
        fontSize: 18,
        fontWeight: "bold",
        width: posterGridWidth,
    },
    tileInner: {
        flexDirection: 'row',
    },
    vote: {
        paddingTop: 8,
        width: 100, // 5 * 20
    },
});

interface MovieListTileProps {
    movie: Movie;
    onMoviePress?: OnMoviePress;
}

export class MovieListTile extends Component<MovieListTileProps> {
    constructor(props: MovieListTileProps) {
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
            defaultSource={R.drawable.outline_image}
            source={{ uri: thumbnailUrl }}
            style={styles.thumbnail as ImageStyle} />;

        let title = MovieClass.displayTitle(movie);
        let titleWidget = <Text style={styles.title} numberOfLines={2}>{title + "\n"}</Text>;

        let voteAverageWidget = <Rating
            type="custom"
            ratingCount={5}
            imageSize={20}
            readonly={true}
            ratingColor={R.color.ratingColor}
            startingValue={movie.vote_average / 2.0}
            style={styles.vote} />;

        let dateValue = Utils.formatDate(MovieClass.date(movie));
        let dateWidget = <Text style={styles.date}>{dateValue}</Text>;

        let summaryWidget = <Text numberOfLines={4} style={styles.summary}>{movie.overview}</Text>;

        return <Pressable onPress={this.onPress?.bind(this)}>
            <Card containerStyle={styles.tile} wrapperStyle={styles.tileInner}>
                {thumbnailWidget}
                <View style={styles.column}>
                    {titleWidget}
                    {voteAverageWidget}
                    {dateWidget}
                    {summaryWidget}
                </View>
            </Card>
        </Pressable>;
    }
}