import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/Media.dart';

import 'dates.dart';
import 'external_ids.dart';
import 'gender.dart';

class Person extends Media {
  final int id;
  final String name;
  final List<String> aliases;
  final String profilePath;
  final Gender gender;
  final DateTime birthday;
  final DateTime deathday;
  final String knownDepartment;
  final String biography;
  final double popularity;
  final String birthplace;
  final bool adult;
  final String imdbId;
  final String homepage;
  final PersonExternalIds externalIds;
  final CreditsResponse credits;

  const Person(
    this.id,
    this.name, {
    this.aliases,
    this.profilePath,
    this.gender = Gender.unknown,
    this.birthday,
    this.deathday,
    this.knownDepartment,
    this.biography,
    this.popularity,
    this.birthplace,
    this.adult,
    this.imdbId,
    this.homepage,
    this.externalIds,
    this.credits,
  }) : super();

  @override
  String toString() {
    return '{id: $id, name: "$name"}';
  }

  /// Creates a [Person] from a JSON object.
  factory Person.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['also_known_as'] as List;
    List<String> aliases = list?.map((i) => i.toString())?.toList();

    return Person(
      json['id'],
      json['name'],
      aliases: aliases,
      profilePath: json['profile_path'],
      gender: Gender_fromJson(json['gender']),
      birthday: parseDateTime(json['birthday']),
      deathday: parseDateTime(json['deathday']),
      knownDepartment: json['known_for_department'],
      biography: json['biography'],
      popularity: json['popularity'],
      birthplace: json['place_of_birth'],
      adult: json['adult'],
      imdbId: json['imdb_id'],
      homepage: json['homepage'],
      externalIds: PersonExternalIds.fromJson(json['external_ids']),
      credits: CreditsResponse.fromJson(json['combined_credits']),
    );
  }
}
