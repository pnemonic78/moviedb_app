import AppState from "@/app/redux/AppState";
import ScreenName from '@/app/ScreenName';
import { MoviesScreen } from '@/features/movies/components/MoviesScreen';
import { topRated } from '@/features/movies/redux/MoviesReducer';
import { Res } from '@/res/Res';
import { Movie } from '@/tmdb_api/model/Movie';
import TMDBApi from '@/tmdb_api/TMDBApi';
import TMDBApiImpl from '@/tmdb_api/TMDBApiImpl';
import { useRouter } from 'expo-router';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

const MoviesTopRatedScreen = () => {

    // TODO inject with provider
    const api = new TMDBApiImpl();

    const router = useRouter();
    const dispatch = useDispatch();
    const movies = useSelector((state: AppState) => state.movies.moviesTopRated);

    useEffect(() => {
        fetchMovies(api);
    }, [api]);

    function fetchMovies(api: TMDBApi) {
        api.getTopRated().then(response => dispatch(topRated(response.results)));
    }

    /// Navigates to the movie details.
    function navigateToMovie(movie: Movie) {
        console.log(`Navigating to movie details: ${movie.title} (${movie.id})`);
        router.push({
            pathname: ScreenName.MOVIE_DETAILS,
            params: { id: movie.id, title: movie.title },
        });
    }

    function onTapMovie(movie: Movie) {
        navigateToMovie(movie);
    }

    return (
        <MoviesScreen title={Res.string.top_rated_movies} movies={movies} onMoviePress={onTapMovie} />
    );
}
export default MoviesTopRatedScreen;