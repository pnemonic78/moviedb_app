import 'package:json_annotation/json_annotation.dart';

import 'model/media_cast.dart';
import 'model/media_crew.dart';

part 'credits_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class CreditsResponse {
  @JsonKey(name: 'id')
  int? id;
  @JsonKey(name: 'cast')
  List<MediaCast> cast;
  @JsonKey(name: 'crew')
  List<MediaCrew> crew;

  CreditsResponse({
    this.id,
    this.cast = const [],
    this.crew = const [],
  });

  /// Creates a [CreditsResponse] from a JSON object.
  static CreditsResponse? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$CreditsResponseFromJson(json);
}
