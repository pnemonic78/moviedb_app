import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';

import 'model/movie.dart';

class Movies {
  static final _movies = <Movie>[];

  static Future<List<Movie>> getMovies(BuildContext context) async {
    if (_movies.isEmpty) {
      final response = await getNowPlaying(context);
      _movies.addAll(response.results);
    }
    return _movies;
  }

  static Future<MoviesResponse> getNowPlaying(
      BuildContext context) async {
    final text = DefaultAssetBundle.of(context)
        .loadString('assets/raw/200/now_playing.json');
    final json = JsonDecoder().convert(await text);
    if (json is! Map) {
      throw ('Data retrieved from API is not a Map');
    }
    return MoviesResponse.fromJson(json);
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

  static Future<VideosResponse> getMovieVideos(
      BuildContext context, int movieId) async {
    try {
      final text = DefaultAssetBundle.of(context)
          .loadString('assets/raw/200/get-movie-videos_$movieId.json');
      final json = JsonDecoder().convert(await text);
      if (json is! Map) {
        throw ('Data retrieved from API is not a Map');
      }
      return VideosResponse.fromJson(json);
    } catch (e) {
      return null;
    }
  }
}
