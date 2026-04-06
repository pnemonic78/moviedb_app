import { ThemedView } from '@/components/themed-view';
import { MoviesCarousel } from '@/features/movies/components/movies-carousel';
import { Movie } from "@/tmdb_api/model/Movie";

export interface MoviesScreenParams {
    title: string,
    movies: Movie[],
    onMoviePress: (movie: Movie) => void
}

export const MoviesScreen = (params: MoviesScreenParams) => {
    return (
        <ThemedView style={{ flex: 1, flexDirection: 'column' }}>
            <MoviesCarousel
                movies={params.movies}
                onPress={params.onMoviePress}
            />
        </ThemedView>
    );
}