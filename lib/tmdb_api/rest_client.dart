import 'package:dio/dio.dart';
import 'package:retrofit/retrofit.dart';

import 'now_playing_response.dart';

part 'rest_client.g.dart';

// `flutter pub run build_runner build`
@RestApi(baseUrl: "https://api.themoviedb.org/3/")
abstract class RestClient {
  factory RestClient(Dio dio, {String baseUrl}) = _RestClient;

  @GET("movie/now_playing")
  Future<MoviesNowPlayingResponse> getMoviesNowPlaying(
      {@Query("api_key") String apiKey,
      @Query("language") String language = "en",
      @Query("page") int page = 1,
      @Query("region") String region});
}
