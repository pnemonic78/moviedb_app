import ScreenName from "@/app/ScreenName";
import { MoviesCarousel } from "@/features/movies/components/movies-carousel";
import { MoviesSection } from "@/features/movies/components/movies-section";
import { Res } from "@/res/Res";
import { Movie } from "@/tmdb_api/model/Movie";
import TMDBApiImpl from "@/tmdb_api/TMDBApiImpl";
import { RelativePathString, useRouter } from 'expo-router';
import { useEffect, useState } from "react";
import { ScrollView, View } from "react-native";

const MoviesAllScreen = () => {

    // TODO inject with provider
    const api = new TMDBApiImpl();

    const [moviesNowPlaying, setMoviesNowPlaying] = useState([]);
    const [moviesPopular, setMoviesPopular] = useState([]);
    const [moviesUpcoming, setMoviesUpcoming] = useState([]);
    const [moviesTopRated, setMoviesTopRated] = useState([]);

    const router = useRouter();

    useEffect(() => {
        // fetch movies for all sections
        const response = require("@/tmdb_api/data/movies.json");
        setMoviesNowPlaying(response.results);
        setMoviesPopular(response.results);
        setMoviesUpcoming(response.results);
        setMoviesTopRated(response.results);
    }, [api]);

    /// Navigates to the movies page.
    function navigateToPage(pageId: RelativePathString) {
        console.log(`Navigating to page: ${pageId}`);
        router.push(pageId);
    }

    /// Navigates to the movie details.
    function navigateToMovie(movie: Movie) {
        console.log(`Navigating to movie details: ${movie.title} (${movie.id})`);
        router.push({
            pathname: ScreenName.MOVIE_DETAILS,
            params: { id: movie.id, title: movie.title },
        });
    }

    function getMoviesNowPlaying() {
        let movies = moviesNowPlaying;
        if (!movies.length) {
            //let dispatch = this.props.dispatch;
            //this.api.getNowPlaying()
            //    .then(data => dispatch(fetchedMoviesNowPlaying(data.results)))
        }
        return movies
    }

    function getMoviesPopular() {
        let movies = moviesPopular;
        if (!movies.length) {
            //let dispatch = this.props.dispatch;
            //this.api.getPopular()
            //    .then(data => dispatch(fetchedMoviesPopular(data.results)))
        }
        return movies
    }

    function getMoviesTopRated() {
        let movies = moviesTopRated;
        if (!movies.length) {
            //let dispatch = this.props.dispatch;
            //this.api.getTopRated()
            //    .then(data => dispatch(fetchedMoviesTopRated(data.results)))
        }
        return movies
    }

    function getMoviesUpcoming() {
        let movies = moviesUpcoming;
        if (!movies.length) {
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
    );
}
export default MoviesAllScreen;