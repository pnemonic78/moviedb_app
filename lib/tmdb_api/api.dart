import 'package:flutter/material.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';

import 'movie.dart';
import 'movies.dart';

class TMDBApi {
  static const image_url = "https://image.tmdb.org/t/p/%s%s";

  static const _original = "original";
  static const backdrop_sizes = [
    "w300",
    "w780",
    "w1280",
    _original,
  ];
  static const logo_sizes = [
    "w45",
    "w92",
    "w154",
    "w185",
    "w300",
    "w500",
    _original,
  ];
  static const poster_sizes = [
    "w92",
    "w154",
    "w185",
    "w342",
    "w500",
    "w780",
    _original,
  ];
  static const profile_sizes = [
    "w45",
    "w185",
    "h632",
    _original,
  ];
  static const still_sizes = [
    "w92",
    "w185",
    "w300",
    _original,
  ];

  static String generatePosterUrl(String path, double width, double height) {
    if ((path == null) || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, poster_sizes);

    return sprintf(image_url, [size, path]);
  }

  static String findSize(double width, double height, List<String> sizes) {
    String result = _original;
    double minDelta = double.infinity;
    double delta;

    for (var size in sizes) {
      if (size.startsWith("w")) {
        final posterWidth = double.parse(size.substring(1));
        delta = (width - posterWidth).abs();
        if (delta < minDelta) {
          result = size;
        }
      } else if (size.startsWith("h")) {
        final posterHeight = double.parse(size.substring(1));
        delta = (height - posterHeight).abs();
        if (delta < minDelta) {
          result = size;
        }
      }
    }

    return result;
  }

  Future<List<Movie>> getMovies(BuildContext context) async {
    return Movies.getMovies(context);
  }

  Future<MovieDetails> _fromMovie(BuildContext context, int movieId) async {
    List<Movie> movies = await getMovies(context);
    Movie movie = movies.firstWhere((movie) => movie.id == movieId);
    return (movie != null) ? MovieDetails.of(movie) : null;
  }

  Future<MovieDetails> getMovie(BuildContext context, int movieId) async {
    List<MovieDetails> movies = Movies.getMovieDetails();
    final e = await _fromMovie(context, movieId);
    return movies.firstWhere((movie) => movie.id == movieId, orElse: () => e);
  }

  Future<MovieDetails> getMovieDetails(Movie movie) async {
    List<MovieDetails> movies = Movies.getMovieDetails();
    return movies.firstWhere((m) => m.id == movie.id, orElse: () => MovieDetails.of(movie));
  }
}
