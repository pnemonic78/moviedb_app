import ScreenName from "@/app/ScreenName";
import { MoviesCarousel } from "@/components/movies/movies-carousel";
import { MoviesSection } from "@/components/movies/movies-section";
import { Res } from "@/res/Res";
import TMDBApiImpl from "@/tmdb_api/TMDBApiImpl";
import { useEffect, useState } from "react";
import { ScrollView, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export const MoviesAllScreen = () => {

    // TODO inject with provider
    const api = new TMDBApiImpl();

    let [moviesNowPlaying, setMoviesNowPlaying] = useState([]);
    let [moviesPopular, setMoviesPopular] = useState([]);
    let [moviesUpcoming, setMoviesUpcoming] = useState([]);
    let [moviesTopRated, setMoviesTopRated] = useState([]);

    useEffect(() => {
        // fetch movies for all sections
        const response = require("@/tmdb_api/data/movies.json");
        setMoviesNowPlaying(response.results);
        setMoviesPopular(response.results);
        setMoviesUpcoming(response.results);
        setMoviesTopRated(response.results);
    }, [api]);

    /// Navigates to the movies page.
    function navigateToPage(pageId) {
        console.log(`Navigating to page: ${pageId}`);
        //let navigation = this.props.navigation
        //navigation.navigate(pageId);
    }

    /// Navigates to the movie details.
    function navigateToMovie(movie) {
        console.log(`Navigating to movie details: ${movie.title}`);
        //let navigation = this.props.navigation
        //navigation.navigate(ScreenName.MOVIE_DETAILS, { movie })
    }

    function getMoviesNowPlaying() {
        let movies = moviesNowPlaying;
        if (!movies?.length) {
            //let dispatch = this.props.dispatch;
            //this.api.getNowPlaying()
            //    .then(data => dispatch(fetchedMoviesNowPlaying(data.results)))
        }
        return movies
    }

    function getMoviesPopular() {
        let movies = moviesPopular;
        if (!movies?.length) {
            //let dispatch = this.props.dispatch;
            //this.api.getPopular()
            //    .then(data => dispatch(fetchedMoviesPopular(data.results)))
        }
        return movies
    }

    function getMoviesTopRated() {
        let movies = moviesTopRated;
        if (!movies?.length) {
            //let dispatch = this.props.dispatch;
            //this.api.getTopRated()
            //    .then(data => dispatch(fetchedMoviesTopRated(data.results)))
        }
        return movies
    }

    function getMoviesUpcoming() {
        let movies = moviesUpcoming;
        if (!movies?.length) {
            //let dispatch = this.props.dispatch;
            //this.api.getUpcoming()
            //    .then(data => dispatch(fetchedMoviesUpcoming(data.results)))
        }
        return movies
    }

    function onClickNowPlaying() {
        navigateToPage(ScreenName.MOVIES_NOW_PLAYING);
    }

    function onClickPopular() {
        navigateToPage(ScreenName.MOVIES_POPULAR);
    }

    function onClickTopRated() {
        navigateToPage(ScreenName.MOVIES_TOP_RATED);
    }

    function onClickUpcoming() {
        navigateToPage(ScreenName.MOVIES_UPCOMING);
    }

    function onTapMovie(movie) {
        navigateToMovie(movie);
    }

    return (
        <SafeAreaView style={{ flex: 1 }}>
            <ScrollView style={{ flex: 1 }}>
                <View style={{ flex: 1, flexDirection: 'column' }}>
                    <MoviesSection label={Res.string.popular} onPress={onClickPopular} />
                    <MoviesCarousel movies={moviesPopular} onPress={onTapMovie} />
                    <MoviesSection label={Res.string.now_playing} onPress={onClickNowPlaying} />
                    <MoviesCarousel movies={moviesNowPlaying} onPress={onTapMovie} />
                    <MoviesSection label={Res.string.upcoming} onPress={onClickUpcoming} />
                    <MoviesCarousel movies={moviesUpcoming} onPress={onTapMovie} />
                    <MoviesSection label={Res.string.top_rated} onPress={onClickTopRated} />
                    <MoviesCarousel movies={moviesTopRated} onPress={onTapMovie} />
                </View>
            </ScrollView>
        </SafeAreaView>
    );
}