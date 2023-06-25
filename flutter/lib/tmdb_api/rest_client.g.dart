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
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/now_playing',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = MoviesResponse.fromJson(_result.data)!;
    return value;
  }

  @override
  getMoviesPopular({required apiKey, required language, page = 1, region}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/popular',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = MoviesResponse.fromJson(_result.data)!;
    return value;
  }

  @override
  getMoviesTopRated({required apiKey, required language, page = 1, region}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/top_rated',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = MoviesResponse.fromJson(_result.data)!;
    return value;
  }

  @override
  getMoviesUpcoming({required apiKey, required language, page = 1, region}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'page': page,
      r'region': region
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/upcoming',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = MoviesResponse.fromJson(_result.data)!;
    return value;
  }

  @override
  getMovieCredits({required moveId, required apiKey}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{r'api_key': apiKey};
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/$moveId/credits',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = CreditsResponse.fromJson(_result.data)!;
    return value;
  }

  @override
  getMovieDetails({required moveId, required apiKey, language = "en-US", append}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'append_to_response': append
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/$moveId',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = MovieDetails.fromJson(_result.data)!;
    return value;
  }

  @override
  getMovieImages({required moveId, required apiKey, language = "en-US", languageInclude = "en"}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'include_image_language': languageInclude
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/$moveId/images',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = ImagesResponse.fromJson(_result.data)!;
    return value;
  }

  @override
  getMovieVideos({required moveId, required apiKey, language = "en-US"}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/movie/$moveId/videos',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = VideosResponse.fromJson(_result.data)!;
    return value;
  }

  @override
  getPerson({required personId, required apiKey, language = "en-US", append}) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{
      r'api_key': apiKey,
      r'language': language,
      r'append_to_response': append
    };
    queryParameters.removeWhere((k, v) => v == null);
    final _data = <String, dynamic>{};
    final Response<Map<String, dynamic>> _result = await _dio.request(
        '$baseUrl/person/$personId',
        queryParameters: queryParameters,
        options: Options(
            method: 'GET',
            headers: <String, dynamic>{},
            extra: _extra,
        ),
        data: _data);
    final value = Person.fromJson(_result.data)!;
    return value;
  }
}
