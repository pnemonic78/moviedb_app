import React from "react";
import { View } from "react-native";
import { MoviesPage, MoviesPageProps } from "./MoviesPage";

export class UpcomingPage extends MoviesPage {
    constructor(props: MoviesPageProps) {
        super(props);
    }

    render() {
        return <View />;
    }
}