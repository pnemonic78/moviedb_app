import { Movie } from "./model/Movie";
import TMBDApi from "./TMDBApi";
import axios from 'axios';
import Keys from '../../keys';

export default class TMBDApiImpl extends TMBDApi {
    readonly _apiKey: string = Keys.apiKey;
    readonly _languageCode = "en";
    readonly _regionCode = "US";

    private async getMovies(url: string): Promise<Movie[]> {
        let result = await axios.get(url);
        let data = result.data;
        let results = data.results as Object[];
        let movies = results.map<Movie>((value) => Movie.fromJson(value))
        return movies;
    }

    async getNowPlaying(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/now_playing?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        return this.getMovies(url);
    }

    async getPopular(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/popular?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        return this.getMovies(url);
    }

    async getTopRated(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/top_rated?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        return this.getMovies(url);
    }

    async getUpcoming(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/upcoming?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        return this.getMovies(url);
    }
}
