import 'package:flutter/foundation.dart';
import 'package:tmdb/tmdb_api/person_credits_response.dart';

import 'dates.dart';
import 'external_ids.dart';
import 'gender.dart';
import 'media.dart';
import 'media_type.dart';

class Person extends Media {
  final List<String> aliases;
  final String biography;
  final DateTime birthday;
  final PersonCreditsResponse credits;
  final String birthplace;
  final DateTime deathday;
  final PersonExternalIds externalIds;
  final Gender gender;
  final String homepage;
  final String imdbId;
  final String knownDepartment;
  final Media media;
  final String name;
  final String profilePath;

  Person({
    this.aliases,
    this.biography,
    this.birthday,
    this.birthplace,
    this.credits,
    this.deathday,
    this.externalIds,
    this.gender = Gender.unknown,
    this.homepage,
    this.imdbId,
    this.knownDepartment,
    @required this.media,
    this.name,
    this.profilePath,
  })  : assert(media != null),
        super(
          adult: media.adult,
          id: media.id,
          mediaType: media.mediaType ?? MediaType.person,
          popularity: media.popularity,
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
      aliases: aliases,
      biography: json['biography'],
      birthday: parseDateTime(json['birthday']),
      birthplace: json['place_of_birth'],
      credits: PersonCreditsResponse.fromJson(json['combined_credits']),
      deathday: parseDateTime(json['deathday']),
      externalIds: PersonExternalIds.fromJson(json['external_ids']),
      gender: Gender.fromJson(json['gender']),
      homepage: json['homepage'],
      imdbId: json['imdb_id'],
      knownDepartment: json['known_for_department'],
      media: Media.fromJson(json),
      name: json['name'],
      profilePath: json['profile_path'],
    );
  }

  @override
  DateTime date() {
    return birthday;
  }

  @override
  String getTitle() {
    return name;
  }
}
