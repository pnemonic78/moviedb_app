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
    this.id,
    this.cast,
    this.crew,
  });

  /// Creates a [PersonCreditsResponse] from a JSON object.
  factory PersonCreditsResponse.fromJson(Map<String, dynamic> json) =>
      (json != null) ? _$PersonCreditsResponseFromJson(json) : null;
}
