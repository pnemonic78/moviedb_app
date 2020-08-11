import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/person_credits_response.dart';

import 'external_ids.dart';
import 'gender.dart';
import 'media_credit.dart';
import 'media_type.dart';

part 'media_cast.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class MediaCast extends MediaCredit {
  @JsonKey(name: 'cast_id')
  int castId;
  @JsonKey(name: 'character')
  String character;
  @JsonKey(name: 'order')
  int order;

  MediaCast({
    MediaCredit credit,
    this.castId,
    this.character,
    this.order,
  }) : super(
          creditId: credit?.creditId,
          media: credit?.media,
          person: credit,
        );

  @override
  String toString() {
    return '{id: $id, name: "$name", character: "$character"}';
  }

  /// Creates a [MediaCast] from a JSON object.
  factory MediaCast.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$MediaCastFromJson(json);
}
