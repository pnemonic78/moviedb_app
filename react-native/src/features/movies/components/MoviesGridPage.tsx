import { Movie } from "@/tmdb_api/model/Movie"
import { OnMoviePress } from "./MovieClickListener"
import { MoviesCarousel } from "./MoviesCarousel"

export type MoviesGridPageParams = {
    movies: Movie[]
    onPress: OnMoviePress
}

export const MoviesGridPage = (params: MoviesGridPageParams) => {
    return (
        <MoviesCarousel movies={params.movies} onPress={params.onPress} />
    )
}