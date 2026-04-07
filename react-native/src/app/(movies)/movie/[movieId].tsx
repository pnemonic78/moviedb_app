import AppState from "@/app/redux/AppState";
import { MoviesDetails } from '@/features/movies/components/MoviesDetails';
import { details } from "@/features/movies/redux/MovieDetailsReducer";
import { Movie } from "@/tmdb_api/model/Movie";
import { MovieDetails } from "@/tmdb_api/model/MovieDetails";
import TMDBApi from "@/tmdb_api/TMDBApi";
import TMDBApiImpl from '@/tmdb_api/TMDBApiImpl';
import { useLocalSearchParams } from 'expo-router';
import { useEffect } from 'react';
import { SafeAreaView } from 'react-native-safe-area-context';
import { useDispatch, useSelector } from 'react-redux';

const MoviesDetailsScreen = () => {
    const params = useLocalSearchParams();
    const movieId = parseInt(params.movieId.toString());
    const movieTitle = params.title;

    // TODO inject with provider
    const api = new TMDBApiImpl();

    const dispatch = useDispatch();
    const movies = useSelector((state: AppState) => state.movieDetails.movies);
    const moviesNowPlaying = useSelector((state: AppState) => state.movies.moviesNowPlaying);
    const moviesPopular = useSelector((state: AppState) => state.movies.moviesPopular);
    const moviesTopRated = useSelector((state: AppState) => state.movies.moviesTopRated);
    const moviesUpcoming = useSelector((state: AppState) => state.movies.moviesUpcoming);

    useEffect(() => {
        fetchMovieDetails(api, movieId)
    }, [api]);

    function fetchMovieDetails(api: TMDBApi, movieId: number) {
        api.getMovieDetailsById(movieId).then(response => dispatch(details(response)));
    }

    function findMovie(movieId: number, movies: Array<Movie>): Movie | undefined {
        return movies.find((item) => item.id === movieId);
    }

    function getMovie(): MovieDetails {
        var movieDetails = movies.find((item) => item.id === movieId);
        if (movieDetails) return movieDetails;
        var movie = findMovie(movieId, moviesNowPlaying);
        if (movie) return (movie as MovieDetails);
        var movie = findMovie(movieId, moviesPopular);
        if (movie) return (movie as MovieDetails);
        var movie = findMovie(movieId, moviesTopRated);
        if (movie) return (movie as MovieDetails);
        var movie = findMovie(movieId, moviesUpcoming);
        if (movie) return (movie as MovieDetails);
        return ({} as MovieDetails);
    }

    return (
        <SafeAreaView style={{ flex: 1 }}>
            <MoviesDetails movie={getMovie()} />
        </SafeAreaView>
    );
}
export default MoviesDetailsScreen;