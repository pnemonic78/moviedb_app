import React, { Component } from "react"
import { Animated, Dimensions, StyleSheet, View } from "react-native"
import { ParamListBase } from "@react-navigation/routers"
import { StackScreenProps } from "@react-navigation/stack"
import { PinchGestureHandler, PinchGestureHandlerStateChangeEvent, State } from "react-native-gesture-handler"
import R from "../../res/R"
import { Movie, MovieClass } from "../../tmdb_api/model/Movie"
import TMDBApi from "../../tmdb_api/TMDBApi"
import { Icon } from "react-native-elements"

interface MoviePosterPageParams extends ParamListBase {
    movie: Movie
}

interface MoviePosterPageProps extends StackScreenProps<MoviePosterPageParams> {
    movie: Movie
}

export class MoviePosterPage extends Component<MoviePosterPageProps> {
    constructor(props: MoviePosterPageProps) {
        super(props)
    }

    private getMovie(): Movie {
        let routeParams = this.props.route.params as MoviePosterPageParams
        return this.props.movie ?? routeParams?.movie
    }

    private setHeaderTitle() {
        let navigation = this.props.navigation
        let movie = this.getMovie()
        navigation.setOptions({
            headerTitle: MovieClass.displayTitle(movie),
        })
    }

    componentDidMount() {
        this.setHeaderTitle()
    }

    private scale = new Animated.Value(1)

    private onPinchEvent = Animated.event(
        [{ nativeEvent: { scale: this.scale } }],
        { useNativeDriver: true }
    )

    private onPinchStateChange(event: PinchGestureHandlerStateChangeEvent) {
        if (event.nativeEvent.oldState === State.ACTIVE) {
            Animated.spring(this.scale, {
                toValue: 1,
                useNativeDriver: true
            }).start()
        }
    }

    render() {
        let movie = this.getMovie()
        let styles = styleSheet

        let size = Dimensions.get("window")
        let screenWidth = size.width
        let screenHeight = size.height
        let imageWidth = screenWidth
        let imageHeight = screenHeight

        let imagePath = movie.poster_path
        let imageUrl = TMDBApi.generatePosterUrl(imagePath, Infinity, Infinity)
        let poster = <Animated.Image
            source={{ uri: imageUrl }}
            resizeMode='contain'
            style={[styles.poster, {
                height: imageHeight,
                width: imageWidth,
                transform: [{ scale: this.scale }],
            }]}
        />

        let posterWidget = <PinchGestureHandler
            onGestureEvent={this.onPinchEvent}
            onHandlerStateChange={this.onPinchStateChange.bind(this)}>
            {poster}
        </PinchGestureHandler>

        let loadingSize = Math.min(imageWidth, imageHeight)
        let loadingWidget = <Icon
            name={R.icon.image.name}
            type={R.icon.image.type}
            size={loadingSize}
            containerStyle={[styles.loading, { width: imageWidth, height: imageHeight, }]}
        />

        return <View>
            {loadingWidget}
            {posterWidget}
        </View>
    }
}

const styleSheet = StyleSheet.create({
    loading: {
        alignContent: 'center',
        justifyContent: 'center',
    },
    poster: {
        position: "absolute",
    }
})