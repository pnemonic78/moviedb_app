import 'package:flutter/foundation.dart';
import 'package:tmdb/tmdb_api/person_credits_response.dart';

import 'dates.dart';
import 'external_ids.dart';
import 'gender.dart';
import 'media.dart';
import 'media_type.dart';

class Person extends Media {
  List<String> aliases;
  String biography;
  DateTime birthday;
  PersonCreditsResponse credits;
  String birthplace;
  DateTime deathday;
  PersonExternalIds externalIds;
  Gender gender;
  String homepage;
  String imdbId;
  String knownDepartment;
  String name;
  String originalName;
  String profilePath;

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
    @required Media media,
    this.name,
    this.originalName,
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
      gender: Gender.valueOf(json['gender']),
      homepage: json['homepage'],
      imdbId: json['imdb_id'],
      knownDepartment: json['known_for_department'],
      media: Media.fromJson(json),
      name: json['name'],
      originalName: json['original_name'],
      profilePath: json['profile_path'],
    );
  }

  @override
  DateTime date() {
    return birthday;
  }

  @override
  String getTitle() {
    return name ?? originalName;
  }
}
