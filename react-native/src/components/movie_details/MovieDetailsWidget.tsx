import React, { Component } from "react";
import { View } from "react-native";
import { Movie } from "../../tmdb_api/model/Movie";
import { MovieDetails } from "../../tmdb_api/model/MovieDetails";

interface MovieDetailsWidgetProps {
    movie: Movie;
// final ValueChanged < MovieDetails > onTapPoster;
// final ValueChanged < MovieVideo > onVideoTap;
// final ValueChanged < MediaCast > onCastTap;
}

interface MovieDetailsWidgetState {
    movie: MovieDetails;
    summaryLinesExpanded: boolean;
// VideosList _videoList;
// CastList _castList;
}

export class MovieDetailsWidget extends Component<MovieDetailsWidgetProps, MovieDetailsWidgetState> {
    constructor(props: MovieDetailsWidgetProps) {
        super(props);
        this.state = {
            movie: MovieDetails.of(props.movie),
            summaryLinesExpanded: false,
        };
    }

    render() {
        return <View style={{ height: 2000, backgroundColor: 'wheat' }} />;
    }
}