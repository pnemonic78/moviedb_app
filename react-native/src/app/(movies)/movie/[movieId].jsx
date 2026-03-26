import { MoviesDetails } from '@/components/movies/movie-details';
import TMDBApiImpl from '@/tmdb_api/TMDBApiImpl';
import { useLocalSearchParams } from 'expo-router';
import { useEffect, useState } from 'react';
import { SafeAreaView } from 'react-native-safe-area-context';

const MoviesDetailsScreen = () => {
    const params = useLocalSearchParams();
    const movieId = parseInt(params.movieId);
    let movieTitle = params.title;

    const [movie, setMovie] = useState({ id: movieId, title: movieTitle });

    // TODO inject with provider
    const api = new TMDBApiImpl();

    useEffect(() => {
        // fetch movie details.
        const movie = require("@/tmdb_api/data/movie_550.json");
        setMovie(movie);
    }, [api]);

    return (
        <SafeAreaView style={{ flex: 1 }}>
            <MoviesDetails movie={movie} />
        </SafeAreaView>
    );
}
export default MoviesDetailsScreen;