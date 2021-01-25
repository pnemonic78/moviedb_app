export default class TMBDApi {
    static api_url = "https://api.themoviedb.org/3/";
    static _image_url = "https://image.tmdb.org/t/p/{size}{path}";
    static _youtube_url = "https://www.youtube.com/watch?v={id}";
    static _youtube_thumbnail_url = "https://img.youtube.com/vi/{id}/0.jpg";
    static _facebook_url = "https://facebook.com/{id}";
    static _imdb_url = "https://imdb.com/name/{id}";
    static _instagram_url = "https://instagram.com/{id}";
    static _twitter_url = "https://twitter.com/{id}";

    static generatePosterUrl(path: string, width: number, height: number) {
        if ((path == null) || (width <= 0) || (height <= 0)) {
            return "";
        }
        const size = "w92";
        return TMBDApi._image_url.replace("{size}", size).replace("{path}", path);
    }
}
