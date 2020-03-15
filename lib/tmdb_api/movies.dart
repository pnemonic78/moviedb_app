import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';
import 'package:tmdb/tmdb_api/now_playing_response.dart';

import 'movie.dart';

class Movies {
  static final _movies = <Movie>[];
  static final _movieDetails = <MovieDetails>[];

  static Future<List<Movie>> getMovies(BuildContext context) async {
    if (_movies.isEmpty) {
      final response = await fetchNowPlaying(context);
      _movies.addAll(response.results);
    }
    return _movies;
  }

  static Future<MoviesNowPlayingResponse> fetchNowPlaying(
      BuildContext context) async {
    final json = DefaultAssetBundle.of(context)
        .loadString('assets/raw/200/now_playing.json');
    final data = JsonDecoder().convert(await json);
    if (data is! Map) {
      throw ('Data retrieved from API is not a Map');
    }
    return MoviesNowPlayingResponse.fromJson(data);
  }

  static List<MovieDetails> getMovieDetails() {
    if (_movieDetails.isEmpty) {
      _movieDetails.add(MovieDetails(
        adult: false,
        backdropPath: "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
        budget: 85000000,
        id: 454626,
        originalLanguage: "en",
        originalTitle: "Sonic the Hedgehog",
        overview:
            "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
        popularity: 294.837,
        posterPath: "/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg",
        releaseDate: DateTime.utc(2020, 2, 12),
        revenue: 265493652,
        runtime: 99,
        title: "Sonic the Hedgehog",
        video: false,
        voteAverage: 7.2,
        voteCount: 862,
      ));
    }
    return _movieDetails;
  }
}
