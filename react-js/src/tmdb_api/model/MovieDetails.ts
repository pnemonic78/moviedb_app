import { CreditsResponse } from "../CreditsResponse"
import { Genre } from "./Genre"
import { Movie, MovieClass } from "./Movie"
import { ProductionCompany } from "./ProductionCompany"
import { ProductionCountry } from "./ProductionCountry"
import { SpokenLanguage } from "./SpokenLanguage"

export interface MovieDetails extends Movie {
    budget: number
    credits?: CreditsResponse
    genres: Genre[]
    homepage: string
    imdb_id?: string
    production_companies: ProductionCompany[]
    production_countries: ProductionCountry[]
    revenue: number
    runtime: number
    spoken_languages: SpokenLanguage[]
    status: string
    tagline?: string
}

export class MovieDetailsClass extends MovieClass {
    static date(movie: MovieDetails): Date | null {
        return MovieClass.date(movie)
    }

    static displayTitle(movie: MovieDetails): string {
        return MovieClass.displayTitle(movie)
    }
}