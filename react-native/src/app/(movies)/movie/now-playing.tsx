import AppState from "@/app/redux/AppState";
import { MoviesScreen } from '@/features/movies/components/MoviesScreen';
import { nowPlaying } from '@/features/movies/redux/MoviesReducer';
import { Res } from '@/res/Res';
import TMDBApi from '@/tmdb_api/TMDBApi';
import TMDBApiImpl from '@/tmdb_api/TMDBApiImpl';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

const MoviesNowPlayingScreen = () => {

    // TODO inject with provider
    const api = new TMDBApiImpl();

    const dispatch = useDispatch();
    const movies = useSelector((state: AppState) => state.movies.moviesNowPlaying);

    useEffect(() => {
        fetchMovies(api);
    }, [api]);

    function fetchMovies(api: TMDBApi) {
        api.getNowPlaying().then(response => dispatch(nowPlaying(response.results)));
    }

    return (
        <MoviesScreen title={Res.string.now_playing_movies} movies={movies} />
    );
}
export default MoviesNowPlayingScreen;