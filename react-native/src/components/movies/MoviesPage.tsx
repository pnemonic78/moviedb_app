import React, { Component } from "react";
import { View } from "react-native";
import { StackScreenProps } from "@react-navigation/stack";
import { Movie } from "../../tmdb_api/model/Movie";

export interface MoviesPageProps extends StackScreenProps<any> {
    movies: Movie[];
}

export abstract class MoviesPage extends Component<MoviesPageProps> {
    constructor(props: MoviesPageProps) {
        super(props);
    }

    render() {
        return <View />;
    }
}