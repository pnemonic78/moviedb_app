import 'package:flutter/foundation.dart';

import 'dates.dart';
import 'media.dart';
import 'person.dart';

class PersonCredit {
  final String backdropPath;
  final String creditId;
  final Media media;
  final List<int> genreIds;
  final List<String> originCountry;
  final String originalLanguage;
  final String originalName;
  final String originalTitle;
  final String overview;
  final Person person;
  final String posterPath;
  final DateTime releaseDate;
  final String title;
  final bool video;
  final double voteAverage;
  final int voteCount;

  PersonCredit({
    this.backdropPath,
    @required this.creditId,
    @required this.media,
    this.genreIds,
    @required this.person,
    this.originCountry,
    this.originalLanguage,
    this.originalName,
    this.originalTitle,
    this.overview,
    this.posterPath,
    this.releaseDate,
    this.title,
    this.video,
    this.voteAverage,
    this.voteCount,
  })  : assert(creditId != null),
        assert(media != null),
        assert(person != null),
        super();

  @override
  String toString() {
    return person.toString();
  }

  /// Creates a [PersonCredit] from a JSON object.
  factory PersonCredit.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['genre_ids'] as List;
    List<int> genreIds = list?.map((i) => i as int)?.toList();

    list = json['origin_country'] as List;
    List<String> countries = list?.map((i) => i.toString())?.toList();

    return PersonCredit(
      backdropPath: json['backdrop_path'],
      creditId: json['credit_id'],
      media: Media.fromJsonType(json),
      genreIds: genreIds,
      originCountry: countries,
      originalLanguage: json['original_language'],
      originalName: json['original_name'],
      originalTitle: json['original_title'],
      overview: json['overview'],
      person: Person.fromJson(json),
      posterPath: json['poster_path'],
      releaseDate: parseDateTime(json['release_date']),
      title: json['title'],
      video: json['video'],
      voteAverage: json['vote_average']?.toDouble(),
      voteCount: json['vote_count'],
    );
  }
}
