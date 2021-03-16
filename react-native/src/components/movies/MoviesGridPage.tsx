import React, { Component, ReactElement } from "react";
import { Dimensions, FlatList, ListRenderItemInfo, StyleSheet } from "react-native";
import { Movie } from "../../tmdb_api/model/Movie";
import { MovieGridTile } from "./MovieGridTile";
import { OnMoviePress } from "./MovieClickListener";

const posterGridWidth = 150.0;

const styleSheet = StyleSheet.create({
    slider: {
    },
});

export interface MoviesGridPageProps {
    movies: Movie[];
    onMoviePress?: OnMoviePress;
}

interface MoviesGridPageState {
    tileWidth: number;
    columnCount: number;
}

export class MoviesGridPage extends Component<MoviesGridPageProps, MoviesGridPageState> {
    constructor(props: MoviesGridPageProps) {
        super(props);
        this.state = {
            tileWidth: posterGridWidth,
            columnCount: 2,
        };
    }

    private onDimensionsChangeBind = this.onDimensionsChange.bind(this);

    private onDimensionsChange() {
        let margin = 16;
        let screenSize = Dimensions.get('window');
        let screenWidth = screenSize.width;
        let cellWidth = margin + posterGridWidth + margin;
        let columnCount = Math.floor(screenWidth / cellWidth);
        let columnWidth = screenWidth / columnCount;

        this.setState({
            tileWidth: columnWidth - margin - margin,
            columnCount: columnCount,
        });
    }

    componentDidMount() {
        Dimensions.addEventListener("change", this.onDimensionsChangeBind);
        this.onDimensionsChange();
    }

    componentWillUnmount() {
        Dimensions.removeEventListener("change", this.onDimensionsChangeBind);
    }

    private renderItem(info: ListRenderItemInfo<Movie>): ReactElement<Movie> {
        return <MovieGridTile
            movie={info.item}
            onMoviePress={this.props.onMoviePress?.bind(this)}
            width={this.state.tileWidth}
        />;
    }

    render() {
        let movies = this.props.movies;

        return <FlatList
            key={this.state.columnCount}
            style={styleSheet.slider}
            data={movies}
            extraData={this}
            horizontal={false}
            renderItem={this.renderItem.bind(this)}
            keyExtractor={(item, i) => item.id.toString()}
            numColumns={this.state.columnCount}
        />;
    }
}