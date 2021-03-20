import React, { Component } from "react";
import { Dimensions, ImageBackground, Platform, StyleSheet, Text } from "react-native";
import { ParamListBase } from "@react-navigation/routers";
import { HeaderBackButton, StackScreenProps } from "@react-navigation/stack";
import { Movie, MovieClass } from "../../tmdb_api/model/Movie";
import { MovieDetailsWidget } from "./MovieDetailsWidget";
import { CollapsibleHeaderScrollView } from "react-native-collapsible-header-views";
import TMDBApi from "../../tmdb_api/TMDBApi";
import R from "../../res/R";
import { MovieDetails } from "../../tmdb_api/model/MovieDetails";
import TMBDApiImpl from "../../tmdb_api/TMDBApiImpl";

interface MovieDetailsHomePageParams extends ParamListBase {
    movie: Movie;
}

interface MovieDetailsHomePageProps extends StackScreenProps<MovieDetailsHomePageParams> {
    movie?: Movie;
}

interface MovieDetailsHomePageState {
    movie: MovieDetails;
}

export class MovieDetailsHomePage extends Component<MovieDetailsHomePageProps, MovieDetailsHomePageState> {
    constructor(props: MovieDetailsHomePageProps) {
        super(props);

        let routeParams = props.route.params as MovieDetailsHomePageParams;
        let movie = props.movie ?? routeParams?.movie;
        this.state = {
            movie: movie as MovieDetails,
        };
        if ((movie as MovieDetails).runtime === undefined) {
            this.fetchMovieDetails(movie)
                .then(data => this.setState({ movie: data }));
        }
    }

    private async fetchMovieDetails(movie: Movie): Promise<MovieDetails> {
        let api: TMDBApi = new TMBDApiImpl();
        return api.getMovieDetails(movie);
    }

    render() {
        let navigation = this.props.navigation;
        let movie = this.state.movie;
        let styles = styleSheet;

        let backButtonWidget = <HeaderBackButton
            style={{width: 40, height: 40}}
            tintColor={'white'}
            onPress={() => navigation.goBack()} />;

        let screenWidth = Dimensions.get("window").width;
        let backdropWidth = screenWidth;
        let backdropHeight = backdropWidth * 9 / 16;
        let backdropUrl = TMDBApi.generateBackdropUrl(
            movie.backdrop_path,
            backdropWidth,
            backdropHeight,
        );

        let title = MovieClass.displayTitle(movie);
        let titleWidget = <Text numberOfLines={3} style={styles.title}>{title}</Text>;

        let headerWidget = <ImageBackground
            source={{ uri: backdropUrl }}
            defaultSource={R.drawable.outline_image}
            style={{ width: backdropWidth, height: backdropHeight, }}
        >
            {backButtonWidget}
            {titleWidget}
        </ImageBackground>;

        return <CollapsibleHeaderScrollView
            CollapsibleHeaderComponent={headerWidget}
            headerHeight={backdropHeight}
            statusBarHeight={Platform.OS === 'ios' ? 20 : 0}
        >
            <MovieDetailsWidget movie={movie} />
        </CollapsibleHeaderScrollView>;
    }
}

const styleSheet = StyleSheet.create({
    title: {
        color: 'white',
        flex: 1,
        textAlignVertical: "bottom",
        fontSize: 32,
        padding: 8,
        textShadowColor: 'black',
        textShadowOffset: { width: 0, height: 0 },
        textShadowRadius: 10,
    },
});
