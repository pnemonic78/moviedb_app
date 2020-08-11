import 'package:flutter/foundation.dart';

import 'person_credit.dart';

class PersonCast extends PersonCredit {
  int castId;
  String character;
  int order;

  PersonCast({
    @required PersonCredit credit,
    this.castId,
    this.character,
    this.order,
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
  factory PersonCast.fromJson(Map<String, dynamic> json) => (json == null)
      ? null
      : PersonCast(
          castId: json['cast_id'],
          character: json['character'],
          credit: PersonCredit.fromJson(json),
          order: json['order'],
        );
}
