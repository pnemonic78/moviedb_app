import { Movie } from "../../tmdb_api/model/Movie";

export declare type OnMoviePress = null | ((movie: Movie) => void);

export declare type OnMoviePressObject = { onMoviePress: OnMoviePress };