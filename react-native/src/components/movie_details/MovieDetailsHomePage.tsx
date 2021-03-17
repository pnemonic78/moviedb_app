import React, { Component } from "react";
import { HeaderBackButton, StackScreenProps } from "@react-navigation/stack";
import { Movie } from "../../tmdb_api/model/Movie";
import { MovieDetailsWidget } from "./MovieDetailsWidget";
import { ParamListBase } from "@react-navigation/routers";
import { CollapsibleHeaderScrollView } from "react-native-collapsible-header-views";
import { Dimensions, ImageBackground, Platform, StyleSheet, Text } from "react-native";
import TMDBApi from "../../tmdb_api/TMDBApi";
import R from "../../res/R";

interface MovieDetailsHomePageParams extends ParamListBase {
    movie: Movie;
}

interface MovieDetailsHomePageProps extends StackScreenProps<MovieDetailsHomePageParams> {
    movie?: Movie;
}

export class MovieDetailsHomePage extends Component<MovieDetailsHomePageProps> {
    constructor(props: MovieDetailsHomePageProps) {
        super(props);
    }

    render() {
        let navigation = this.props.navigation;
        let routeParams = this.props.route.params as MovieDetailsHomePageParams;
        let movie = this.props.movie ?? routeParams?.movie;
        let styles = styleSheet;

        let backButtonWidget = <HeaderBackButton
            style={{width: 40, height: 40,}}
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

        let title = movie.title ?? movie.original_title;//movie.getTitle() ?? "";
        let titleWidget = <Text numberOfLines={3} style={styles.title}>{title}</Text>;

        let headerWidget = <ImageBackground
            source={{ uri: backdropUrl }}
            defaultSource={R.images.outline_image}
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
