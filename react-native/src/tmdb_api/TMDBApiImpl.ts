import Movie from "./model/Movie";
import TMBDApi from "./TMDBApi";

export default class TMBDApiImpl extends TMBDApi {
    async getNowPlaying(): Promise<Movie[]> {
        let data = await require('./data.json');
        return data.results;
    }

    async getPopular(): Promise<Movie[]> {
        let data = await require('./data.json');
        return data.results;
    }

    async getTopRated(): Promise<Movie[]> {
        let data = await require('./data.json');
        return data.results;
    }

    async getUpcoming(): Promise<Movie[]> {
        let data = await require('./data.json');
        return data.results;
    }
}
