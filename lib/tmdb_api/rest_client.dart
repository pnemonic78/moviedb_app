import 'package:dio/dio.dart';
import 'package:retrofit/retrofit.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

import 'images_response.dart';
import 'model/movie_details.dart';
import 'now_playing_response.dart';
import 'videos_response.dart';

part 'rest_client.g.dart';

// `flutter pub run build_runner build`
@RestApi(baseUrl: "https://api.themoviedb.org/3")
abstract class RestClient {
  factory RestClient(Dio dio, {String baseUrl}) = _RestClient;

  @GET("/movie/now_playing")
  Future<MoviesNowPlayingResponse> getMoviesNowPlaying({
    @Query("api_key") String apiKey,
    @Query("language") String language = "en-US",
    @Query("page") int page = 1,
    @Query("region") String region,
  });

  @GET("/movie/{movie_id}/credits")
  Future<CreditsResponse> getMovieCredits({
    @Path("movie_id") int moveId,
    @Query("api_key") String apiKey,
  });

  @GET("/movie/{movie_id}")
  Future<MovieDetails> getMovieDetails({
    @Path("movie_id") int moveId,
    @Query("api_key") String apiKey,
    @Query("language") String language = "en-US",
    @Query("append_to_response") String append,
  });

  @GET("/movie/{movie_id}/images")
  Future<ImagesResponse> getMovieImages({
    @Path("movie_id") int moveId,
    @Query("api_key") String apiKey,
    @Query("language") String language = "en-US",
    @Query("include_image_language") String languageInclude = "en",
  });

  @GET("/movie/{movie_id}/videos")
  Future<VideosResponse> getMovieVideos({
    @Path("movie_id") int moveId,
    @Query("api_key") String apiKey,
    @Query("language") String language = "en-US",
  });

  @GET("/person/{person_id}")
  Future<Person> getPerson({
    @Path("person_id") int personId,
    @Query("api_key") String apiKey,
    @Query("language") String language = "en-US",
  });
}
