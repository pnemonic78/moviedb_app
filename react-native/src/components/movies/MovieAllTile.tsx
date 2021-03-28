import React, { Component } from 'react'
import { GestureResponderEvent, ImageStyle, Pressable, StyleSheet, Text } from 'react-native'
import { Card } from 'react-native-elements'
import TMBDApi from '../../tmdb_api/TMDBApi'
import R from '../../res/R'
import { LoadingImage } from "../LoadingImage"
import { Movie, MovieClass } from '../../tmdb_api/model/Movie'
import { OnMoviePress } from './MovieClickListener'

const posterGridWidth = R.dimen.posterGridWidth
const posterGridHeight = R.dimen.posterGridHeight

const styleSheet = StyleSheet.create({
    thumbnail: {
        borderTopLeftRadius: R.dimen.cardRadius,
        borderTopRightRadius: R.dimen.cardRadius,
        height: posterGridHeight,
        width: posterGridWidth,
    },
    tile: {
        borderRadius: R.dimen.cardRadius,
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
})

interface MovieAllTileProps {
    movie: Movie
    onMoviePress?: OnMoviePress
}

export class MovieAllTile extends Component<MovieAllTileProps> {
    constructor(props: MovieAllTileProps) {
        super(props)
        this.styles = styleSheet
    }

    private styles: StyleSheet.NamedStyles<any>

    private onPress(_event: GestureResponderEvent) {
        let movie = this.props.movie
        this.props.onMoviePress?.(movie)
    }

    render() {
        let movie = this.props.movie
        let styles = this.styles

        let imageWidth = styles.thumbnail.width as number
        let imageHeight = styles.thumbnail.height as number
        let thumbnailUrl = TMBDApi.generatePosterUrl(movie.poster_path, imageWidth, imageHeight)
        let thumbnailWidget = <LoadingImage
            defaultSource={R.drawable.outline_image}
            source={{ uri: thumbnailUrl }}
            style={styles.thumbnail as ImageStyle} />

        let title = MovieClass.displayTitle(movie)
        let titleWidget = <Text style={styles.title} numberOfLines={2}>{title + "\n"}</Text>

        return <Pressable onPress={this.onPress?.bind(this)}>
            <Card containerStyle={styles.tile}>
                {thumbnailWidget}
                {titleWidget}
            </Card>
        </Pressable>
    }
}