import { Keys } from "@/keys"
import axios from "axios"
import { MovieDetails } from "./model/MovieDetails"
import { MoviesResponse } from "./model/MoviesResponse"
import { Person } from "./model/Person"
import TMBDApi from "./TMDBApi"

export default class TMBDApiImpl extends TMBDApi {
    readonly apiKey: string = Keys.apiKey
    readonly languageCode = "en"
    readonly regionCode = "US"

    private async getMovies(url: string): Promise<MoviesResponse> {
        return (await axios.get<MoviesResponse>(url)).data
    }

    async getNowPlaying(): Promise<MoviesResponse> {
        let url = TMBDApi.api_url + "movie/now_playing?api_key=" + this.apiKey + "&language=" + this.languageCode + "&page=1"
        return this.getMovies(url)
    }

    async getPopular(): Promise<MoviesResponse> {
        let url = TMBDApi.api_url + "movie/popular?api_key=" + this.apiKey + "&language=" + this.languageCode + "&page=1"
        return this.getMovies(url)
    }

    async getTopRated(): Promise<MoviesResponse> {
        let url = TMBDApi.api_url + "movie/top_rated?api_key=" + this.apiKey + "&language=" + this.languageCode + "&page=1"
        return this.getMovies(url)
    }

    async getUpcoming(): Promise<MoviesResponse> {
        let url = TMBDApi.api_url + "movie/upcoming?api_key=" + this.apiKey + "&language=" + this.languageCode + "&page=1"
        return this.getMovies(url)
    }

    async getMovieDetailsById(movieId: number): Promise<MovieDetails> {
        let url = TMBDApi.api_url + "movie/" + movieId + "?api_key=" + this.apiKey + "&language=" + this.languageCode + "&append_to_response=credits"
        let result = await axios.get(url)
        return result.data as MovieDetails
    }

    async getPersonById(personId: number): Promise<Person> {
        let url = TMBDApi.api_url + "person/" + personId + "?api_key=" + this.apiKey + "&language=" + this.languageCode + "&append_to_response=external_ids,combined_credits"
        let result = await axios.get(url)
        return result.data as Person
    }
}
