import { Movie } from "@/tmdb_api/model/Movie"
import { OnMoviePress } from "./MovieClickListener"
import { MoviesCarousel } from "./MoviesCarousel"

export type MoviesListPageParams = {
    movies: Movie[]
    onPress: OnMoviePress
}

export const MoviesListPage = (params: MoviesListPageParams) => {
    return (
        <MoviesCarousel movies={params.movies} onPress={params.onPress} />
    )
}