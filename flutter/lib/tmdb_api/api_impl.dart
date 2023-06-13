import 'package:flutter/material.dart';
import 'package:tmdb/keys.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';

import 'api.dart';
import 'rest_client.dart';

class TMDBApiImpl extends TMDBApi {
  static const _apiKey = Keys.apiKey;

  final RestClient _client;

  TMDBApiImpl(this._client) : assert(_client != null);

  Future<MoviesResponse?> getNowPlaying(BuildContext context) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMoviesNowPlaying(
      apiKey: _apiKey,
      language: locale.languageCode,
    );
  }

  Future<MoviesResponse?> getPopular(BuildContext context) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMoviesPopular(
      apiKey: _apiKey,
      language: locale.languageCode,
    );
  }

  Future<MoviesResponse?> getTopRated(BuildContext context) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMoviesTopRated(
      apiKey: _apiKey,
      language: locale.languageCode,
    );
  }

  Future<MoviesResponse?> getUpcoming(BuildContext context) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMoviesUpcoming(
      apiKey: _apiKey,
      language: locale.languageCode,
    );
  }

  Future<CreditsResponse?> getMovieCreditsById(
      BuildContext context, int movieId) async {
    return _client.getMovieCredits(
      apiKey: _apiKey,
      moveId: movieId,
    );
  }

  Future<MovieDetails?> getMovieDetailsById(
      BuildContext context, int movieId) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMovieDetails(
      apiKey: _apiKey,
      moveId: movieId,
      language: locale.languageCode,
      append: "credits",
    );
  }

  Future<VideosResponse?> getMovieVideosById(
      BuildContext context, int movieId) async {
    return _client.getMovieVideos(
      apiKey: _apiKey,
      moveId: movieId,
    );
  }

  Future<Person?> getPersonById(BuildContext context, int personId) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getPerson(
      apiKey: _apiKey,
      personId: personId,
      language: locale.languageCode,
      append: "external_ids,combined_credits",
    );
  }
}
