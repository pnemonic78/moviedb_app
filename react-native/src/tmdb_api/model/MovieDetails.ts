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
}