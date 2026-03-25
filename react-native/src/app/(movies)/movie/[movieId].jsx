import { MoviesDetails } from '@/components/movies/movie-details';
import TMDBApiImpl from '@/tmdb_api/TMDBApiImpl';
import { useLocalSearchParams } from 'expo-router';
import { useEffect, useState } from 'react';

const MoviesDetailsScreen = () => {
    const params = useLocalSearchParams();
    const movieId = parseInt(params.movieId);
    let movieTitle = params.title;

    const [movie, setMovie] = useState({ id: movieId, title: movieTitle });

    // TODO inject with provider
    const api = new TMDBApiImpl();

    useEffect(() => {
        // fetch movie details.
        const response = require("@/tmdb_api/data/movies.json");
        const movie = response.results.find(m => m.id == movieId);
        setMovie(movie);
    }, [api]);

    return (
        <MoviesDetails movie={movie} />
    );
}
export default MoviesDetailsScreen;