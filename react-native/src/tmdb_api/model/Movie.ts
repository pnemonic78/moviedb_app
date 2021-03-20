import { Media, MediaClass } from "./Media";

export interface Movie extends Media {
    backdrop_path: string;
    genre_ids: number[];
    origin_country: string;
    original_language: string;
    original_title: string;
    overview: string;
    poster_path: string;
    release_date?: Date;
    title: string;
    video: boolean;
    vote_average: number;
    vote_count: number;
}

export class MovieClass extends MediaClass {
    static date(movie: Movie): Date | null {
        return movie.release_date ?? null;
    }

    static displayTitle(movie: Movie): string {
        return movie.title ?? movie.original_title;
    }
}