import 'package:flutter/foundation.dart';

import 'dates.dart';
import 'media.dart';

class Movie extends Media {
  final String backdropPath;
  final List<int> genreIds;
  final String originalLanguage;
  final String originalTitle;
  final String overview;
  final String posterPath;
  final DateTime releaseDate;
  final String title;
  final bool video;
  final double voteAverage;
  final int voteCount;

  const Movie(final int id,
      {final bool adult,
      this.backdropPath,
      this.genreIds,
      this.originalLanguage,
      @required this.originalTitle,
      this.overview,
      final double popularity,
      this.posterPath,
      this.releaseDate,
      @required this.title,
      this.video,
      this.voteAverage,
      this.voteCount})
      : super(
          id,
          adult: adult,
          popularity: popularity,
        );

  /// Creates a [Movie] from a JSON object.
  factory Movie.fromJson(Map<String, dynamic> json) {
    var list = json['genre_ids'] as List;
    List<int> genreIds = list.map((i) => i as int).toList();

    return Movie(
      json['id'],
      adult: json['adult'],
      backdropPath: json['backdrop_path'],
      genreIds: genreIds,
      originalLanguage: json['original_language'],
      originalTitle: json['original_title'],
      overview: json['overview'],
      popularity: json['popularity'].toDouble(),
      posterPath: json['poster_path'],
      releaseDate: parseDateTime(json['release_date']),
      title: json['title'],
      video: json['video'],
      voteAverage: json['vote_average'].toDouble(),
      voteCount: json['vote_count'],
    );
  }
}
