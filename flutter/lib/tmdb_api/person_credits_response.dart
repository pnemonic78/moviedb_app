import 'package:json_annotation/json_annotation.dart';

import 'model/person_cast.dart';
import 'model/person_crew.dart';

part 'person_credits_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class PersonCreditsResponse {
  @JsonKey(name: 'id')
  int id;
  @JsonKey(name: 'cast')
  List<PersonCast> cast;
  @JsonKey(name: 'crew')
  List<PersonCrew> crew;

  PersonCreditsResponse({
    required this.id,
    this.cast = const [],
    this.crew = const [],
  });

  /// Creates a [PersonCreditsResponse] from a JSON object.
  static PersonCreditsResponse? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$PersonCreditsResponseFromJson(json);
}
