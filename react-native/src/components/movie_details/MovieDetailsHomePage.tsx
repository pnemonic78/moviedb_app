import React, { Component } from "react"
import { Dimensions, ImageBackground, Platform, StyleSheet, Text, View } from "react-native"
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
import { ScreenName } from "../main/ScreenName"
import { MediaCast } from "../../tmdb_api/model/MediaCast"
import { LoadingIcon } from "../LoadingIcon"

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

        let movie = this.getMovie()
        this.fetchMovieDetails(movie)
    }

    private api: TMDBApi = new TMDBApiImpl()

    private getMovie(): MovieDetails {
        let routeParams = this.props.route.params as MovieDetailsHomePageParams
        let movieProp = this.props.movie ?? routeParams?.movie
        let movieId = movieProp?.id ?? 0
        let movie = this.props.movies.get(movieId) ?? (movieProp as MovieDetails)
        return movie
    }

    private async fetchMovieDetails(movie: Movie) {
        let dispatch = this.props.dispatch;
        let movies = this.props.movies
        if (!movies.has(movie.id)) {
            this.api.getMovieDetails(movie)
                .then(data => dispatch(fetchedMovieDetails(data)))
        }
    }

    private onTapPoster(movie: Movie) {
        let navigation = this.props.navigation
        navigation.navigate(ScreenName.MOVIE_POSTER, { movie: movie })
    }

    private onTapCast(cast: MediaCast) {
        let navigation = this.props.navigation
        navigation.navigate(ScreenName.PERSON_DETAILS, { person: cast })
    }

    render() {
        let navigation = this.props.navigation
        let movie = this.getMovie()
        let styles = styleSheet

        let backButtonWidget = <HeaderBackButton
            style={styles.backButton}
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

        let headerWidget = <View style={styles.header}>
            <LoadingIcon
                placeholder={R.icon.image}
                source={{ uri: backdropUrl }}
                style={{ width: backdropWidth, height: backdropHeight, position: "absolute", }}
            />
            <View style={[styles.overlay, { height: backdropHeight }]}>
                {backButtonWidget}
                {titleWidget}
            </View>
        </View>

        let content = <MovieDetailsWidget
            movie={movie}
            onPosterPress={this.onTapPoster.bind(this)}
            onCastPress={this.onTapCast.bind(this)}    
        />

        return <CollapsibleHeaderScrollView
            CollapsibleHeaderComponent={headerWidget}
            headerHeight={backdropHeight}
            statusBarHeight={Platform.OS === 'ios' ? 20 : 0}
        >
            {content}   
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
    backButton: {
        flex: 0,
        marginVertical: R.dimen.actionBarButtonMargin,
        width: R.dimen.actionBarButtonSize,
        height: R.dimen.actionBarButtonSize,
    },
    header: {
    },
    overlay: {
        display: "flex",
        flexDirection: "column",
        position: "absolute",
    },
    title: {
        color: 'white',
        flex: 1,
        fontSize: 32,
        padding: 8,
        textAlignVertical: "bottom",
        textShadowColor: 'black',
        textShadowOffset: { width: 0, height: 0 },
        textShadowRadius: 10,
    },
})
