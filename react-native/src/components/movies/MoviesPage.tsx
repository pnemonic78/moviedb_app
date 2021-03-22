import React, { Component } from "react"
import { GestureResponderEvent, Pressable } from "react-native"
import { ParamListBase } from "@react-navigation/routers"
import { StackScreenProps } from "@react-navigation/stack"
import { Movie } from "../../tmdb_api/model/Movie"
import { MoviesListPage } from "./MoviesListPage"
import { OnMoviePress } from "./MovieClickListener"
import { MoviesGridPage } from "./MoviesGridPage"
import { ScreenName } from "../main/ScreenName"
import { Image } from "react-native-elements"
import R from "../../res/R"

export interface MoviesPageParams extends ParamListBase {
    movies: Movie[]
}

export interface MoviesPageProps extends StackScreenProps<MoviesPageParams> {
    movies: Movie[]
    onMoviePress?: OnMoviePress
}

interface MoviesPageState {
    showAsList: boolean
}

export abstract class MoviesPage extends Component<MoviesPageProps, MoviesPageState> {
    constructor(props: MoviesPageProps) {
        super(props)
        this.state = { showAsList: false }
    }

    private getIconViewStyle(): React.ReactNode {
        return <Pressable
            onPress={this.toggleViewStyle.bind(this)}
            android_ripple={{ color: 'black' }}
        >
            <Image
                source={this.state.showAsList ? R.drawable.outline_grid : R.drawable.outline_list}
                style={{
                    height: R.dimen.actionBarButtonSize,
                    width: R.dimen.actionBarButtonSize,
                    margin: R.dimen.actionBarButtonMargin,
                }}
            />
        </Pressable>
    }

    private toggleViewStyle(event: GestureResponderEvent) {
        let showAsOther = !this.state.showAsList
        this.setState({ showAsList: showAsOther }, this.setHeaderActions)
    }

    /// Navigates to the movie details.
    private navigateToMovie(movie: Movie) {
        let navigation = this.props.navigation
        navigation.navigate(ScreenName.MOVIE_DETAILS, { movie })
    }

    private setHeaderActions() {
        let navigation = this.props.navigation
        navigation.setOptions({
            headerRight: this.getIconViewStyle.bind(this),
        })
    }

    componentDidMount() {
        this.setHeaderActions()
    }

    render() {
        let routeParams = this.props.route.params as MoviesPageParams
        let movies = this.props.movies ?? routeParams.movies
        let onMovieTap = this.props.onMoviePress?.bind(this) ?? this.navigateToMovie.bind(this)

        return this.state.showAsList
            ? <MoviesListPage
                movies={movies}
                onMoviePress={onMovieTap}
                />
            : <MoviesGridPage
                movies={movies}
                onMoviePress={onMovieTap}
                />
    }
}