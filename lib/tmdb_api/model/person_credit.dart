import 'package:tmdb/tmdb_api/model/media_type.dart';

import 'dates.dart';
import 'external_ids.dart';
import 'gender.dart';
import 'person.dart';

class PersonCredit extends Person {
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
  final String posterPath;
  final DateTime releaseDate;
  final String title;
  final bool video;
  final double voteAverage;
  final int voteCount;

  const PersonCredit(
    final int id,
    final String name,
    this.creditId, {
    // person
    final List<String> aliases,
    final String profilePath,
    final Gender gender,
    final DateTime birthday,
    final DateTime deathday,
    final String knownDepartment,
    final String biography,
    final double popularity,
    final String birthplace,
    final bool adult,
    final String imdbId,
    final String homepage,
    final PersonExternalIds externalIds,
    // credit
    this.backdropPath,
    this.episodeCount,
    this.firstAirDate,
    this.genreIds,
    this.mediaType,
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
  }) : super(
          id,
          name,
          // person
          aliases: aliases,
          profilePath: profilePath,
          gender: gender,
          birthday: birthday,
          deathday: deathday,
          knownDepartment: knownDepartment,
          biography: biography,
          popularity: popularity,
          birthplace: birthplace,
          adult: adult,
          imdbId: imdbId,
          homepage: homepage,
          externalIds: externalIds,
        );

  /// Creates a [PersonCredit] from a JSON object.
  factory PersonCredit.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    final person = Person.fromJson(json);

    var list = json['genre_ids'] as List;
    List<int> genreIds = list?.map((i) => i as int)?.toList();

    list = json['origin_country'] as List;
    List<String> countries = list?.map((i) => i.toString())?.toList();

    return PersonCredit(
      person.id,
      person.name,
      json['credit_id'],

      // person
      aliases: person.aliases,
      profilePath: person.profilePath,
      gender: person.gender,
      birthday: person.birthday,
      deathday: person.deathday,
      knownDepartment: person.knownDepartment,
      biography: person.biography,
      popularity: person.popularity,
      birthplace: person.birthplace,
      adult: person.adult,
      imdbId: person.imdbId,
      homepage: person.homepage,
      externalIds: person.externalIds,

      // credit
      backdropPath: json['backdrop_path'],
      episodeCount: json['episode_count'],
      firstAirDate: parseDateTime(json['first_air_date']),
      genreIds: genreIds,
      mediaType: MediaType.fromJson(json['media_type']),
      originCountry: countries,
      originalLanguage: json['original_language'],
      originalName: json['original_name'],
      originalTitle: json['original_title'],
      overview: json['overview'],
      posterPath: json['poster_path'],
      releaseDate: parseDateTime(json['release_date']),
      title: json['title'],
      video: json['video'],
      voteAverage: json['vote_average']?.toDouble(),
      voteCount: json['vote_count'],
    );
  }
}
