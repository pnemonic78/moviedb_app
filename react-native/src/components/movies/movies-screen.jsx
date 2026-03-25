import { MoviesCarousel } from '@/components/movies/movies-carousel';
import { ThemedView } from '@/components/themed-view';
import TMDBApiImpl from "@/tmdb_api/TMDBApiImpl";
import { useEffect, useState } from "react";

export const MoviesScreen = ({ title, onMoviePress }) => {

    // TODO inject with provider
    const api = new TMDBApiImpl();

    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const response = require("@/tmdb_api/data/movies.json");
        setMovies(response.results);
    }, [api]);

    /// Navigates to the movie details.
    function navigateToMovie(movie) {
        console.log(`Navigating to movie details: ${movie.title} (${movie.id})`);
        router.push({
            pathname: ScreenName.MOVIE_DETAILS,
            params: { id: movie.id, title: movie.title },
        });
    }

    function onTapMovie(movie) {
        navigateToMovie(movie);
    }


    return (
        <ThemedView style={{ flex: 1, flexDirection: 'column' }}>
            <MoviesCarousel
                movies={movies}
                onPress={onTapMovie}
            />
        </ThemedView>
    );
}