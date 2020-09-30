import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:tmdb/keys.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';

import 'api.dart';
import 'movies_response.dart';
import 'rest_client.dart';

class TMDBApiImpl extends TMDBApi {
  static const _api_url = "https://api.themoviedb.org/3/";
  static const _apiKey = Keys.apiKey;

  RestClient _client;

  TMDBApiImpl() {
    final dio = Dio();
    _client = RestClient(dio, baseUrl: _api_url);
  }

  Future<MoviesResponse> getNowPlaying(BuildContext context) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMoviesNowPlaying(
      apiKey: _apiKey,
      language: locale.languageCode,
    );
  }

  Future<CreditsResponse> getMovieCreditsById(
      BuildContext context, int movieId) async {
    return _client.getMovieCredits(
      apiKey: _apiKey,
      moveId: movieId,
    );
  }

  Future<MovieDetails> getMovieDetailsById(
      BuildContext context, int movieId) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMovieDetails(
      apiKey: _apiKey,
      moveId: movieId,
      language: locale.languageCode,
      append: "credits",
    );
  }

  Future<VideosResponse> getMovieVideosById(
      BuildContext context, int movieId) async {
    return _client.getMovieVideos(
      apiKey: _apiKey,
      moveId: movieId,
    );
  }

  Future<Person> getPersonById(BuildContext context, int personId) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getPerson(
      apiKey: _apiKey,
      personId: personId,
      language: locale.languageCode,
      append: "external_ids,combined_credits",
    );
  }
}
