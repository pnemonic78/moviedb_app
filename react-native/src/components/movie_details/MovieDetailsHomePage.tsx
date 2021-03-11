import React, { Component } from "react";
import { View } from "react-native";
import { StackScreenProps } from "@react-navigation/stack";
import { Movie } from "../../tmdb_api/model/Movie";

interface MovieDetailsHomePageProps extends StackScreenProps<any> {
    movie: Movie;
}

export class MovieDetailsHomePage extends Component<MovieDetailsHomePageProps> {
    constructor(props: MovieDetailsHomePageProps) {
        super(props);
    }

    render() {
        return <View />;
    }
}