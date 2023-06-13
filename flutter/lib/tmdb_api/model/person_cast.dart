import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';

import 'media.dart';
import 'person.dart';
import 'person_credit.dart';

part 'person_cast.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class PersonCast extends PersonCredit {
  @JsonKey(name: 'cast_id')
  int castId;
  @JsonKey(name: 'character')
  String character;
  @JsonKey(name: 'order')
  int order;

  PersonCast({
    required PersonCredit credit,
    required this.castId,
    required this.character,
    this.order = 0,
  }) : super(
          creditId: credit.creditId,
          media: credit.media,
          person: credit.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", character: "$character"}';
  }

  /// Creates a [PersonCast] from a JSON object.
  static PersonCast? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$PersonCastFromJson(json);
}
