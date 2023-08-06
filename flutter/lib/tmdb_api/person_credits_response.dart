import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

import 'model/person_cast.dart';
import 'model/person_crew.dart';

part 'person_credits_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class PersonCreditsResponse {
  @JsonKey(name: 'id')
  int? id;
  @JsonKey(name: 'cast')
  List<PersonCast> cast;
  @JsonKey(name: 'crew')
  List<PersonCrew> crew;

  PersonCreditsResponse({
    this.id,
    this.cast = const [],
    this.crew = const [],
  });

  /// Creates a [PersonCreditsResponse] from a JSON object.
  static PersonCreditsResponse? fromJson(
    Map<String, dynamic>? json,
    Person person,
  ) =>
      (json == null) ? null : _$PersonCreditsResponseFromJson(json, person);
}
