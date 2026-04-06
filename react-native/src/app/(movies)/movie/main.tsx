import AppState from "@/app/redux/AppState";
import ScreenName from "@/app/ScreenName";
import { MoviesCarousel } from "@/features/movies/components/movies-carousel";
import { MoviesSection } from "@/features/movies/components/movies-section";
import { nowPlaying, popular, topRated, upcoming } from "@/features/movies/redux/MoviesReducer";
import { Res } from "@/res/Res";
import { Movie } from "@/tmdb_api/model/Movie";
import TMDBApi from "@/tmdb_api/TMDBApi";
import TMDBApiImpl from "@/tmdb_api/TMDBApiImpl";
import { ExternalPathString, RelativePathString, useRouter } from 'expo-router';
import { useEffect } from "react";
import { ScrollView, View } from "react-native";
import { useDispatch, useSelector } from "react-redux";

const MoviesAllScreen = () => {

    // TODO inject with provider
    const api = new TMDBApiImpl();

    const router = useRouter();
    const dispatch = useDispatch();
    const moviesNowPlaying = useSelector((state: AppState) => state.movies.moviesNowPlaying);
    const moviesPopular = useSelector((state: AppState) => state.movies.moviesPopular);
    const moviesTopRated = useSelector((state: AppState) => state.movies.moviesTopRated);
    const moviesUpcoming = useSelector((state: AppState) => state.movies.moviesUpcoming);

    useEffect(() => {
        // fetch movies for all sections
        fetchMoviesNowPlaying(api);
        fetchMoviesPopular(api);
        fetchMoviesTopRated(api);
        fetchMoviesUpcoming(api);
    }, [api]);

    /// Navigates to the movies page.
    function navigateToPage(pageId: RelativePathString | ExternalPathString) {
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

    function fetchMoviesNowPlaying(api: TMDBApi) {
        api.getNowPlaying().then(response => dispatch(nowPlaying(response.results)));
    }

    function fetchMoviesPopular(api: TMDBApi) {
        api.getPopular().then(response => dispatch(popular(response.results)));
    }

    function fetchMoviesTopRated(api: TMDBApi) {
        api.getTopRated().then(response => dispatch(topRated(response.results)));
    }

    function fetchMoviesUpcoming(api: TMDBApi) {
        api.getUpcoming().then(response => dispatch(upcoming(response.results)));
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

    function onTapMovie(movie: Movie) {
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