import 'package:flutter/foundation.dart';

import 'person_credit.dart';

class PersonCast extends PersonCredit {
  final int castId;
  final String character;
  final PersonCredit credit;
  final int order;

  PersonCast({
    this.castId,
    this.character,
    @required this.credit,
    this.order,
  }) : super(
          creditId: credit.creditId,
          media: credit.media,
          originCountry: credit.originCountry,
          originalName: credit.originalName,
          person: credit.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", character: "$character"}';
  }

  /// Creates a [PersonCast] from a JSON object.
  factory PersonCast.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return PersonCast(
      castId: json['cast_id'],
      character: json['character'],
      credit: PersonCredit.fromJson(json),
      order: json['order'],
    );
  }
}
