import { Media } from "./Media";

export class Movie extends Media {
    backdrop_path: string = "";
    genre_ids: number[] = [];
    origin_country: string = "";
    original_language: string = "";
    original_title: string = "";
    overview: string = "";
    poster_path: string = "";
    release_date: Date | null = null;
    title: string = "";
    video: boolean = false;
    vote_average: number = 0;
    vote_count: number = 0;

    date(): Date | null {
        return this.release_date ?? null;
    }

    getTitle(): string | null {
        return this.title ?? this.original_title ?? null;
    }
}