import React, { Component } from "react";
import { GestureResponderEvent } from "react-native";
import { ParamListBase } from "@react-navigation/routers";
import { StackScreenProps } from "@react-navigation/stack";
import { Movie } from "../../tmdb_api/model/Movie";
import { MoviesListPage } from "./MoviesListPage";
import { OnMoviePress } from "./MovieClickListener";
import { MoviesGridPage } from "./MoviesGridPage";
import { ImageButton } from "../ImageButton";
import R from "../../res/R";

export interface MoviesPageParams extends ParamListBase {
    movies: Movie[];
}

export interface MoviesPageProps extends StackScreenProps<MoviesPageParams> {
    movies: Movie[];
    onMoviePress?: OnMoviePress;
}

interface MoviesPageState {
    showAsList: boolean;
}

export abstract class MoviesPage extends Component<MoviesPageProps, MoviesPageState> {
    constructor(props: MoviesPageProps) {
        super(props);
        this.state = { showAsList: false };
    }

    private getIconViewStyle(): React.ReactNode {
        return <ImageButton
            source={this.state.showAsList ? R.images.outline_grid : R.images.outline_list}
            onPress={this.toggleViewStyle.bind(this)}
            style={{ height: 32, margin: 8, width: 32 }}
            />;
    }

    private toggleViewStyle(event: GestureResponderEvent) {
        let showAsOther = !this.state.showAsList;
        this.setState({ showAsList: showAsOther }, this.setHeaderActions);
    }

    /// Navigates to the movie details.
    private navigateToMovie(movie: Movie) {
        let navigation = this.props.navigation;
        navigation.navigate("MovieDetails", { movie });
    }

    private setHeaderActions() {
        let navigation = this.props.navigation;
        navigation.setOptions({
            headerRight: this.getIconViewStyle.bind(this),
        });
    }

    componentDidMount() {
        this.setHeaderActions();
    }

    render() {
        let routeParams = this.props.route.params as MoviesPageParams;
        let movies = this.props.movies ?? routeParams.movies;
        let onMovieTap = this.props.onMoviePress?.bind(this) ?? this.navigateToMovie.bind(this);

        return this.state.showAsList
            ? <MoviesListPage
                movies={movies}
                onMoviePress={onMovieTap}
                />
            : <MoviesGridPage
                movies={movies}
                onMoviePress={onMovieTap}
                />;
    }
}