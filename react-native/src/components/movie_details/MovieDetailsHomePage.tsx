import React, { Component } from "react"
import { Dimensions, ImageBackground, Platform, StyleSheet, Text } from "react-native"
import { ParamListBase } from "@react-navigation/routers"
import { HeaderBackButton, StackScreenProps } from "@react-navigation/stack"
import { Movie, MovieClass } from "../../tmdb_api/model/Movie"
import { MovieDetailsWidget } from "./MovieDetailsWidget"
import { CollapsibleHeaderScrollView } from "react-native-collapsible-header-views"
import TMDBApi from "../../tmdb_api/TMDBApi"
import R from "../../res/R"
import { MovieDetails } from "../../tmdb_api/model/MovieDetails"
import { connect, DispatchProp } from "react-redux"
import { MoviesAction } from "../../redux/actions/MoviesAction"
import { AppReducersState } from "../../redux/reducers/AppReducer"
import TMDBApiImpl from "../../tmdb_api/TMDBApiImpl"
import { fetchedMovieDetails } from "../../redux/actions/MovieDetailsAction"

interface MovieDetailsHomePageParams extends ParamListBase {
    movie: Movie
}

interface MovieDetailsHomePageProps extends StackScreenProps<MovieDetailsHomePageParams>, DispatchProp<MoviesAction> {
    movie?: Movie
    movies: Map<number, MovieDetails>
}

export class MovieDetailsHomePageComponent extends Component<MovieDetailsHomePageProps> {
    constructor(props: MovieDetailsHomePageProps) {
        super(props)

        let routeParams = props.route.params as MovieDetailsHomePageParams
        let movie = props.movie ?? routeParams?.movie
        this.fetchMovieDetails(movie)
    }

    private api: TMDBApi = new TMDBApiImpl()

    private async fetchMovieDetails(movie: Movie) {
        let dispatch = this.props.dispatch;
        let movies = this.props.movies
        if (!movies.has(movie.id)) {
            this.api.getMovieDetails(movie)
                .then(data => dispatch(fetchedMovieDetails(data)))
        }
    }

    render() {
        let navigation = this.props.navigation
        let routeParams = this.props.route.params as MovieDetailsHomePageParams
        let movieProp = this.props.movie ?? routeParams?.movie
        let movieId = movieProp?.id ?? 0
        let movie = this.props.movies.get(movieId) ?? (movieProp as MovieDetails)
        let styles = styleSheet

        let actionBarButtonMargin = R.dimen.actionBarButtonMargin
        let actionBarButtonSize = R.dimen.actionBarButtonSize
        let backButtonWidget = <HeaderBackButton
            style={{
                marginTop: actionBarButtonMargin,
                width: actionBarButtonSize,
                height: actionBarButtonSize
            }}
            tintColor={'white'}
            onPress={() => navigation.goBack()} />

        let screenWidth = Dimensions.get("window").width
        let backdropWidth = screenWidth
        let backdropHeight = backdropWidth * 9 / 16
        let backdropUrl = TMDBApi.generateBackdropUrl(
            movie.backdrop_path,
            backdropWidth,
            backdropHeight,
        )

        let title = MovieClass.displayTitle(movie)
        let titleWidget = <Text numberOfLines={3} style={styles.title}>{title}</Text>

        let headerWidget = <ImageBackground
            source={{ uri: backdropUrl }}
            defaultSource={R.drawable.outline_image}
            style={{ width: backdropWidth, height: backdropHeight, }}
        >
            {backButtonWidget}
            {titleWidget}
        </ImageBackground>

        return <CollapsibleHeaderScrollView
            CollapsibleHeaderComponent={headerWidget}
            headerHeight={backdropHeight}
            statusBarHeight={Platform.OS === 'ios' ? 20 : 0}
        >
            <MovieDetailsWidget movie={movie} navigation={navigation} route={this.props.route} />
        </CollapsibleHeaderScrollView>
    }
}

function mapStateToProps(state: AppReducersState): object {
    const stateReducer = state.movieDetailsReducer;
    return {
        movies: stateReducer.movies,
    }
}

export const MovieDetailsHomePage = connect(mapStateToProps)(MovieDetailsHomePageComponent)

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
})
