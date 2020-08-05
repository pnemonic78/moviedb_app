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
          creditId: credit.creditId,
          movie: credit.movie,
          originCountry: credit.originCountry,
          person: credit.person,
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
