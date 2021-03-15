import React, { Component } from "react";
import { StackScreenProps } from "@react-navigation/stack";
import { Movie } from "../../tmdb_api/model/Movie";
import { MoviesListPage } from "./MoviesListPage";
import { OnMoviePress } from "./MovieClickListener";
import { MoviesGridPage } from "./MoviesGridPage";
import { ParamListBase } from "@react-navigation/routers";

export interface MoviesPageParams extends ParamListBase {
    movies: Movie[];
    showAsList: object | undefined;// boolean;
}

export interface MoviesPageProps extends StackScreenProps<MoviesPageParams> {
    movies: Movie[];
    showAsList: boolean;
    onMoviePress?: OnMoviePress;
}

export abstract class MoviesPage extends Component<MoviesPageProps> {
    constructor(props: MoviesPageProps) {
        super(props);
    }

    /// Navigates to the movie details.
    private navigateToMovie(movie: Movie) {
        let navigation = this.props.navigation;
        navigation.navigate("MovieDetails", { movie });
    }

    render() {
        let routeParams = this.props.route.params as MoviesPageParams;
        let showAsList = this.props.showAsList ?? routeParams.showAsList;
        let movies = this.props.movies ?? routeParams.movies;
        let onMovieTap = this.props.onMoviePress?.bind(this) ?? this.navigateToMovie.bind(this);

        return showAsList
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