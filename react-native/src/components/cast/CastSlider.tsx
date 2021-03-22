import React, { Component, ReactElement } from 'react'
import { FlatList, ListRenderItemInfo, StyleSheet } from 'react-native'
import { Movie } from '../../tmdb_api/model/Movie'
import { CastTile } from './CastTile'
import { OnCastPress } from './CastClickListener'
import { MediaCast } from '../../tmdb_api/model/MediaCast'

const styleSheet = StyleSheet.create({
    slider: {
    },
})

interface CastSliderProps {
    cast: MediaCast[]
    onCastPress?: OnCastPress
}

export class CastSlider extends Component<CastSliderProps> {
    constructor(props: CastSliderProps) {
        super(props)
    }

    private renderItem(info: ListRenderItemInfo<MediaCast>): ReactElement<Movie> {
        return <CastTile cast={info.item} onCastPress={this.props.onCastPress?.bind(this)} />
    }

    render() {
        let cast = this.props.cast

        return <FlatList
            style={styleSheet.slider}
            data={cast}
            extraData={this}
            horizontal={true}
            renderItem={this.renderItem.bind(this)}
            keyExtractor={(item, i) => item.id.toString()}
            />
    }
}
