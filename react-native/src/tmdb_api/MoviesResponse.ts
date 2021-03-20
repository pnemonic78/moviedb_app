import { Dates } from "./model/Dates";
import { Movie } from "./model/Movie";

export interface MoviesResponse {
    results: Movie[];
    dates: Dates;
    page: number;
    total_pages: number;
    total_results: number;
}