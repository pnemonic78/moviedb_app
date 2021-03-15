import React, { Component, ReactElement } from "react";
import { FlatList, ListRenderItemInfo, StyleSheet } from "react-native";
import { Movie } from "../../tmdb_api/model/Movie";
import { MovieGridTile } from "./MovieGridTile";
import { OnMoviePress } from "./MovieClickListener";

const styleSheet = StyleSheet.create({
    slider: {
    },
});

export interface MoviesGridPageProps {
    movies: Movie[];
    onMoviePress?: OnMoviePress;
}

export class MoviesGridPage extends Component<MoviesGridPageProps> {
    constructor(props: MoviesGridPageProps) {
        super(props);
    }

    private renderItem(info: ListRenderItemInfo<Movie>): ReactElement<Movie> {
        return <MovieGridTile movie={info.item} onMoviePress={this.props.onMoviePress?.bind(this)} />;
    }

    render() {
        let movies = this.props.movies;

        return <FlatList
            style={styleSheet.slider}
            data={movies}
            extraData={this}
            horizontal={false}
            renderItem={this.renderItem.bind(this)}
            keyExtractor={(item, i) => item.id.toString()}
            numColumns={2}
        />;
    }
}