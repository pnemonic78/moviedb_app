import AppState from "@/app/redux/AppState";
import { MoviesScreen } from '@/features/movies/components/MoviesScreen';
import { popular } from '@/features/movies/redux/MoviesReducer';
import { Res } from '@/res/Res';
import TMDBApi from '@/tmdb_api/TMDBApi';
import TMDBApiImpl from '@/tmdb_api/TMDBApiImpl';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

const MoviesPopularScreen = () => {

    // TODO inject with provider
    const api = new TMDBApiImpl();

    const dispatch = useDispatch();
    const movies = useSelector((state: AppState) => state.movies.moviesPopular);

    useEffect(() => {
        fetchMovies(api);
    }, [api]);

    function fetchMovies(api: TMDBApi) {
        api.getPopular().then(response => dispatch(popular(response.results)));
    }

    return (
        <MoviesScreen title={Res.string.popular_movies} movies={movies} />
    );
}
export default MoviesPopularScreen;