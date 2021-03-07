import Movie from "./model/Movie";
import TMBDApi from "./TMDBApi";
import axios from 'axios';
import Keys from '../../keys';

export default class TMBDApiImpl extends TMBDApi {
    readonly _apiKey: string = Keys.apiKey;
    readonly _languageCode = "en";
    readonly _regionCode = "US";

    async getNowPlaying(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/now_playing?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        let result = await axios.get(url);
        let data = result.data;
        return data.results;
    }

    async getPopular(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/popular?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        let result = await axios.get(url);
        let data = result.data;
        return data.results;
    }

    async getTopRated(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/top_rated?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        let result = await axios.get(url);
        let data = result.data;
        return data.results;
    }

    async getUpcoming(): Promise<Movie[]> {
        let url = TMBDApi.api_url + "movie/upcoming?api_key=" + this._apiKey + "&language=" + this._languageCode + "&page=1";
        let result = await axios.get(url);
        let data = result.data;
        return data.results;
    }
}
