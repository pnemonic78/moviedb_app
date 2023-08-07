import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:retrofit/retrofit.dart';

import 'credits_response.dart';
import 'images_response.dart';
import 'model/movie_details.dart';
import 'model/person.dart';
import 'movies_response.dart';
import 'tv_response.dart';
import 'videos_response.dart';

part 'rest_client.g.dart';

// `flutter pub run build_runner build --delete-conflicting-outputs`
@RestApi(baseUrl: "https://api.themoviedb.org/3")
abstract class RestClient {
  @factoryMethod
  factory RestClient(Dio dio, String baseUrl) = _RestClient;

  @factoryMethod
  static RestClient of(Dio dio) => RestClient(dio, '');

  /// Get a list of movies that are currently in theatres.
  @GET("/movie/now_playing")
  Future<MoviesResponse> getMoviesNowPlaying({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
    @Query("region") String? region,
  });

  /// Get a list of movies ordered by popularity.
  @GET("/movie/popular")
  Future<MoviesResponse> getMoviesPopular({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
    @Query("region") String? region,
  });

  /// Get a list of movies ordered by rating.
  @GET("/movie/top_rated")
  Future<MoviesResponse> getMoviesTopRated({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
    @Query("region") String? region,
  });

  /// Get a list of movies that are being released soon.
  @GET("/movie/upcoming")
  Future<MoviesResponse> getMoviesUpcoming({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
    @Query("region") String? region,
  });

  @GET("/movie/{movie_id}/credits")
  Future<CreditsResponse> getMovieCredits({
    @Path("movie_id") required int moveId,
    @Query("api_key") required String apiKey,
  });

  @GET("/movie/{movie_id}")
  Future<MovieDetails> getMovieDetails({
    @Path("movie_id") required int moveId,
    @Query("api_key") required String apiKey,
    @Query("language") String language = "en-US",
    @Query("append_to_response") String? append,
  });

  /// Get the images that belong to a movie.
  @GET("/movie/{movie_id}/images")
  Future<ImagesResponse> getMovieImages({
    @Path("movie_id") required int moveId,
    @Query("api_key") required String apiKey,
    @Query("language") String language = "en-US",
    @Query("include_image_language") String languageInclude = "en",
  });

  @GET("/movie/{movie_id}/videos")
  Future<VideosResponse> getMovieVideos({
    @Path("movie_id") required int moveId,
    @Query("api_key") required String apiKey,
    @Query("language") String language = "en-US",
  });

  /// Query the top level details of a person.
  @GET("/person/{person_id}")
  Future<Person> getPerson({
    @Path("person_id") required int personId,
    @Query("api_key") required String apiKey,
    @Query("language") String language = "en-US",
    @Query("append_to_response") String? append,
  });

  /// Get a list of TV shows airing today.
  @GET("/tv/airing_today")
  Future<TelevisionResponse> getTVAiringToday({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
    @Query("timezone") String timezone = "",
  });

  /// Get a list of TV shows that air in the next 7 days.
  @GET("/tv/on_the_air")
  Future<TelevisionResponse> getTVOnTheAir({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
    @Query("timezone") String timezone = "",
  });

  /// Get a list of TV shows ordered by popularity.
  @GET("/tv/popular")
  Future<TelevisionResponse> getTVPopular({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
  });

  /// Get a list of TV shows ordered by rating.
  @GET("/tv/top_rated")
  Future<TelevisionResponse> getTVTopRated({
    @Query("api_key")  required String apiKey,
    @Query("language") required String language,
    @Query("page") int page = 1,
  });
}
