import React, { Component } from "react"
import { Animated, Dimensions } from "react-native"
import { ParamListBase } from "@react-navigation/routers"
import { StackScreenProps } from "@react-navigation/stack"
import { PinchGestureHandler, PinchGestureHandlerStateChangeEvent, State } from "react-native-gesture-handler"
import R from "../../res/R"
import { Movie, MovieClass } from "../../tmdb_api/model/Movie"
import TMDBApi from "../../tmdb_api/TMDBApi"

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

    private setHeaderTitle() {
        let navigation = this.props.navigation
        let routeParams = this.props.route.params as MoviePosterPageParams
        let movie = this.props.movie ?? routeParams?.movie
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
        let routeParams = this.props.route.params as MoviePosterPageParams
        let movie = this.props.movie ?? routeParams?.movie

        let size = Dimensions.get("window")
        let screenWidth = size.width
        let screenHeight = size.height
        let imageWidth = screenWidth
        let imageHeight = screenHeight

        let imagePath = movie.poster_path
        let imageUrl = TMDBApi.generatePosterUrl(imagePath, Infinity, Infinity)
        let poster = <Animated.Image
            defaultSource={R.drawable.outline_image}
            source={{ uri: imageUrl }}            
            resizeMode='contain'
            style={{
                height: imageHeight,
                width: imageWidth,
                transform: [{ scale: this.scale }],
            }}
        />
        
        let posterWidget = <PinchGestureHandler
            onGestureEvent={this.onPinchEvent}
            onHandlerStateChange={this.onPinchStateChange.bind(this)}>
            {poster}
        </PinchGestureHandler>

        return posterWidget
    }
}