import { Dates } from "./Dates"
import { Movie } from "./Movie"

export interface MoviesResponse {
    results: Movie[]
    dates: Dates
    page: number
    total_pages: number
    total_results: number
}