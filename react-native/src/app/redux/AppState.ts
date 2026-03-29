import MovieDetailsState from "../../features/movies/redux/MovieDetailsState";
import MoviesState from "../../features/movies/redux/MoviesState";
import PersonDetailsState from "../../features/people/redux/PersonDetailsState";

export default interface AppState {
    movies: MoviesState,
    movieDetails: MovieDetailsState,
    personDetails: PersonDetailsState,
}
