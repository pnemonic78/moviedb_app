import 'package:flutter/foundation.dart';

import 'dates.dart';
import 'media_type.dart';
import 'person.dart';

class MovieCredit extends Person {
  final String creditId;
  final String backdropPath;
  final int episodeCount;
  final DateTime firstAirDate;
  final List<int> genreIds;
  final MediaType mediaType;
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

  MovieCredit({
    this.backdropPath,
    @required this.creditId,
    this.episodeCount,
    this.firstAirDate,
    this.genreIds,
    this.mediaType,
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
        super(
          aliases: person.aliases,
          biography: person.biography,
          birthday: person.birthday,
          birthplace: person.birthplace,
          deathday: person.deathday,
          externalIds: person.externalIds,
          gender: person.gender,
          homepage: person.homepage,
          imdbId: person.imdbId,
          knownDepartment: person.knownDepartment,
          media: person.media,
          name: person.name,
          profilePath: person.profilePath,
        );

  /// Creates a [MovieCredit] from a JSON object.
  factory MovieCredit.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['genre_ids'] as List;
    List<int> genreIds = list?.map((i) => i as int)?.toList();

    list = json['origin_country'] as List;
    List<String> countries = list?.map((i) => i.toString())?.toList();

    return MovieCredit(
      backdropPath: json['backdrop_path'],
      creditId: json['credit_id'],
      episodeCount: json['episode_count'],
      firstAirDate: parseDateTime(json['first_air_date']),
      genreIds: genreIds,
      mediaType: MediaType.fromJson(json['media_type']),
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
