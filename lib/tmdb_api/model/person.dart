import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/person_credits_response.dart';

import 'date_converter.dart';
import 'external_ids.dart';
import 'gender.dart';
import 'media.dart';
import 'media_type.dart';

part 'person.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieDateTimeConverter()
class Person extends Media {
  @JsonKey(name: 'also_known_as')
  List<String> aliases;
  @JsonKey(name: 'biography')
  String biography;
  @JsonKey(name: 'birthday')
  DateTime birthday;
  @JsonKey(name: 'combined_credits')
  PersonCreditsResponse credits;
  @JsonKey(name: 'place_of_birth')
  String birthplace;
  @JsonKey(name: 'deathday')
  DateTime deathday;
  @JsonKey(name: 'external_ids')
  PersonExternalIds externalIds;
  @JsonKey(name: 'gender')
  Gender gender;
  @JsonKey(name: 'homepage')
  String homepage;
  @JsonKey(name: 'imdb_id')
  String imdbId;
  @JsonKey(name: 'known_for_department')
  String knownDepartment;
  @JsonKey(name: 'name')
  String name;
  @JsonKey(name: 'original_name')
  String originalName;
  @JsonKey(name: 'profile_path')
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
  })  : super(
          adult: media?.adult,
          id: media?.id,
          mediaType: media?.mediaType ?? MediaType.person,
          popularity: media?.popularity,
        );

  @override
  String toString() {
    return '{id: $id, name: "$name"}';
  }

  /// Creates a [Person] from a JSON object.
  factory Person.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$PersonFromJson(json);

  @override
  DateTime date() {
    return birthday;
  }

  @override
  String getTitle() {
    return name ?? originalName;
  }
}
