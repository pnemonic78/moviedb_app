import { Genre } from "./Genre";
import { Movie } from "./Movie";
import { ProductionCompany } from "./ProductionCompany";
import { ProductionCountry } from "./ProductionCountry";
import { SpokenLanguage } from "./SpokenLanguage";

export class MovieDetails extends Movie {
    budget: number = 0;
    credits: any;
    genres: Genre[] = [];
    homepage: string = "";
    imdb_id: string = "";
    production_companies: ProductionCompany[] = [];
    production_countries: ProductionCountry[] = [];
    revenue: number = 0;
    runtime: number = 0;
    spoken_languages: SpokenLanguage[] = [];
    status: string = "";
    tagline: string = "";

    static of(movie: Movie): MovieDetails {
        let result = new MovieDetails();
        Object.assign(result, movie);
        return result;
    }

    // Map the POJO to object with methods.
    static fromJson(json: any): MovieDetails {
        let result = new MovieDetails();
        Object.assign(result, json);
        return result;
    }
}