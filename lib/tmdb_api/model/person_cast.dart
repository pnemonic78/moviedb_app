import 'package:flutter/foundation.dart';

import 'person_credit.dart';

class PersonCast extends PersonCredit {
  final int castId;
  final String character;
  final PersonCredit credit;
  final int order;

  PersonCast({
    this.castId,
    this.character,
    @required this.credit,
    this.order,
  }) : super(
          backdropPath: credit.backdropPath,
          creditId: credit.creditId,
          creditMedia: credit.creditMedia,
          episodeCount: credit.episodeCount,
          firstAirDate: credit.firstAirDate,
          genreIds: credit.genreIds,
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

  /// Creates a [PersonCast] from a JSON object.
  factory PersonCast.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return PersonCast(
      castId: json['cast_id'],
      character: json['character'],
      credit: PersonCredit.fromJson(json),
      order: json['order'],
    );
  }
}
