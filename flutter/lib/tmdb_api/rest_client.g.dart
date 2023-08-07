// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'rest_client.dart';

// **************************************************************************
// RetrofitGenerator
// **************************************************************************

class _RestClient implements RestClient {
  _RestClient(this._dio, this.baseUrl) {
    ArgumentError.checkNotNull(_dio, '_dio');
  }

  final Dio _dio;

  final String baseUrl;

  @override
  getMoviesNowPlaying({required apiKey, required language, page = 1, region}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/now_playing',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = MoviesResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getMoviesPopular({required apiKey, required language, page = 1, region}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/popular',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = MoviesResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getMoviesTopRated({required apiKey, required language, page = 1, region}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/top_rated',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = MoviesResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getMoviesUpcoming({required apiKey, required language, page = 1, region}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/upcoming',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = MoviesResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getMovieCredits({required moveId, required apiKey}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{r'api_key': apiKey};
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/$moveId/credits',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = CreditsResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getMovieDetails({required moveId, required apiKey, language = "en-US", append}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'append_to_response': append
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/$moveId',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = MovieDetails.fromJson(result.data)!;
    return value;
  }

  @override
  getMovieImages({required moveId, required apiKey, language = "en-US", languageInclude = "en"}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'include_image_language': languageInclude
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/$moveId/images',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = ImagesResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getMovieVideos({required moveId, required apiKey, language = "en-US"}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/movie/$moveId/videos',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = VideosResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getPerson({required personId, required apiKey, language = "en-US", append}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'append_to_response': append
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/person/$personId',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: extra,
        ),
        data: null);
    final value = Person.fromJson(result.data)!;
    return value;
  }

  @override
  getTVAiringToday({required apiKey, required language, page = 1, timezone = ""}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'timezone': timezone
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/tv/airing_today',
        queryParameters: queryParameters,
        options: Options(
          method: 'GET',
          headers: <String, dynamic>{},
          extra: extra,
        ),
        data: null);
    final value = TelevisionResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getTVOnTheAir({required apiKey, required language, page = 1, timezone = ""}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'timezone': timezone
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/tv/on_the_air',
        queryParameters: queryParameters,
        options: Options(
          method: 'GET',
          headers: <String, dynamic>{},
          extra: extra,
        ),
        data: null);
    final value = TelevisionResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getTVPopular({required apiKey, required language, page = 1}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/tv/popular',
        queryParameters: queryParameters,
        options: Options(
          method: 'GET',
          headers: <String, dynamic>{},
          extra: extra,
        ),
        data: null);
    final value = TelevisionResponse.fromJson(result.data)!;
    return value;
  }

  @override
  getTVTopRated({required apiKey, required language, page = 1}) async {
    const extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page
    };
    queryParameters.removeWhere((k, v) => v == null);
    final Response<Map<String, dynamic>> result = await _dio.request(
        '/tv/top_rated',
        queryParameters: queryParameters,
        options: Options(
          method: 'GET',
          headers: <String, dynamic>{},
          extra: extra,
        ),
        data: null);
    final value = TelevisionResponse.fromJson(result.data)!;
    return value;
  }
}
