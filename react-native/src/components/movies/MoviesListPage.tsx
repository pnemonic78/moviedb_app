import React, { Component, ReactElement } from "react"
import { FlatList, ListRenderItemInfo, StyleSheet } from "react-native"
import { Movie } from "../../tmdb_api/model/Movie"
import { MovieListTile } from "./MovieListTile"
import { OnMoviePress } from "./MovieClickListener"

const styleSheet = StyleSheet.create({
    slider: {
    },
})

export interface MoviesListPageProps {
    movies: Movie[]
    onMoviePress?: OnMoviePress
}

export class MoviesListPage extends Component<MoviesListPageProps> {
    constructor(props: MoviesListPageProps) {
        super(props)
    }

    private renderItem(info: ListRenderItemInfo<Movie>): ReactElement<Movie> {
        return <MovieListTile
            movie={info.item}
            onMoviePress={this.props.onMoviePress?.bind(this)} />
    }

    render() {
        let movies = this.props.movies

        return <FlatList
            style={styleSheet.slider}
            data={movies}
            extraData={this}
            horizontal={false}
            renderItem={this.renderItem.bind(this)}
            keyExtractor={(item, i) => item.id.toString()}
        />
    }
}