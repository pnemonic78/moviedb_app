import React, { Component } from "react"

import R from "../../res/R"
import { MediaCast } from "../../tmdb_api/model/MediaCast"
import TMDBApi from "../../tmdb_api/TMDBApi"
import { LoadingIcon } from "../LoadingIcon"
import { GestureResponderEvent, Pressable, StyleSheet, Text } from "react-native"
import { Card } from "react-native-elements"
import { OnCastPress } from "./CastClickListener"

interface CastTileProps {
    cast: MediaCast,
    onCastPress?: OnCastPress
}

export class CastTile extends Component<CastTileProps> {
    constructor(props: CastTileProps) {
        super(props)
    }

    private onPress(_event: GestureResponderEvent) {
        let cast = this.props.cast
        this.props.onCastPress?.(cast)
    }

    render() {
        let cast = this.props.cast
        let styles = styleSheet

        let imageWidth = R.dimen.castTileWidth
        let imageHeight = R.dimen.castTileHeight
        let thumbnailUrl = TMDBApi.generateProfileThumbnail(
            cast.profile_path,
            imageWidth,
            imageHeight,
        )
        let thumbnailWidget = <LoadingIcon
            placeholder={R.icon.face}
            source={{ uri: thumbnailUrl }}
            style={styles.thumbnail} />

        let titleWidget = <Text style={styles.title} numberOfLines={2}>{cast.name + "\n"}</Text>

        let characterWidget = <Text style={styles.subtitle} numberOfLines={2}>{cast.character + "\n"}</Text>

        return <Pressable onPress={this.onPress?.bind(this)}>
            <Card containerStyle={styles.tile}>
                {thumbnailWidget}
                {titleWidget}
                {characterWidget}
            </Card>
        </Pressable>
    }
}

const styleSheet = StyleSheet.create({
    subtitle: {
        fontSize: 16,
        fontWeight: "bold",
        paddingStart: 8,
        paddingEnd: 8,
        paddingTop: 4,
    },
    thumbnail: {
        borderTopLeftRadius: R.dimen.cardRadius,
        borderTopRightRadius: R.dimen.cardRadius,
        height: R.dimen.castTileHeight,
        width: R.dimen.castTileWidth,
    },
    tile: {
        borderRadius: R.dimen.cardRadius,
        flex: 1,
        flexShrink: 1,
        paddingLeft: 0,
        paddingRight: 0,
        paddingTop: 0,
        width: R.dimen.castTileWidth,
    },
    title: {
        fontSize: 18,
        fontWeight: "bold",
        paddingStart: 8,
        paddingEnd: 8,
        paddingTop: 4,
    },
})
