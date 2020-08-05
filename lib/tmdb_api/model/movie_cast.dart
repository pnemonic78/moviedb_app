import 'package:flutter/foundation.dart';

import 'movie_credit.dart';

class MovieCast extends MovieCredit {
  final int castId;
  final String character;
  final MovieCredit credit;
  final int order;

  MovieCast({
    this.castId,
    this.character,
    @required this.credit,
    this.order,
  }) : super(
          backdropPath: credit.backdropPath,
          creditId: credit.creditId,
          genreIds: credit.genreIds,
          movie: credit.movie,
          originCountry: credit.originCountry,
          originalLanguage: credit.originalLanguage,
          originalName: credit.originalName,
          originalTitle: credit.originalTitle,
          overview: credit.overview,
          person: credit.person,
          posterPath: credit.posterPath,
          releaseDate: credit.releaseDate,
          title: credit.title,
          video: credit.video,
          voteAverage: credit.voteAverage,
          voteCount: credit.voteCount,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", character: "$character"}';
  }

  /// Creates a [MovieCast] from a JSON object.
  factory MovieCast.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return MovieCast(
      castId: json['cast_id'],
      character: json['character'],
      credit: MovieCredit.fromJson(json),
      order: json['order'],
    );
  }
}
