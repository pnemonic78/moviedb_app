import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';
import 'package:tmdb/tmdb_api/now_playing_response.dart';

import 'movie.dart';

class Movies {
  static final _movies = <Movie>[];

  static Future<List<Movie>> getMovies(BuildContext context) async {
    if (_movies.isEmpty) {
      final response = await fetchNowPlaying(context);
      _movies.addAll(response.results);
    }
    return _movies;
  }

  static Future<MoviesNowPlayingResponse> fetchNowPlaying(
      BuildContext context) async {
    final text = DefaultAssetBundle.of(context)
        .loadString('assets/raw/200/now_playing.json');
    final json = JsonDecoder().convert(await text);
    if (json is! Map) {
      throw ('Data retrieved from API is not a Map');
    }
    return MoviesNowPlayingResponse.fromJson(json);
  }

  static Future<MovieDetails> getMovieDetails(
      BuildContext context, int movieId) async {
    try {
      final text = DefaultAssetBundle.of(context)
          .loadString('assets/raw/200/details_$movieId.json');
      final json = JsonDecoder().convert(await text);
      if (json is! Map) {
        throw ('Data retrieved from API is not a Map');
      }
      return MovieDetails.fromJson(json);
    } catch (e) {
      return null;
    }
  }
}
