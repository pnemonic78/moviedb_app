import 'package:tmdb/tmdb_api/credits_response.dart';

import 'dates.dart';
import 'external_ids.dart';
import 'gender.dart';
import 'media.dart';

class Person extends Media {
  final String name;
  final List<String> aliases;
  final String profilePath;
  final Gender gender;
  final DateTime birthday;
  final DateTime deathday;
  final String knownDepartment;
  final String biography;
  final String birthplace;
  final String imdbId;
  final String homepage;
  final PersonExternalIds externalIds;
  final CreditsResponse credits;

  const Person(
    final int id,
    this.name, {
    this.aliases,
    this.profilePath,
    this.gender = Gender.unknown,
    this.birthday,
    this.deathday,
    this.knownDepartment,
    this.biography,
    final double popularity,
    this.birthplace,
    final bool adult,
    this.imdbId,
    this.homepage,
    this.externalIds,
    this.credits,
  }) : super(
          id,
          adult: adult,
          popularity: popularity,
        );

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
      gender: Gender.fromJson(json['gender']),
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
