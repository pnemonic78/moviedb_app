import 'package:flutter/foundation.dart';

import 'media.dart';
import 'person.dart';

class PersonCredit {
  final String creditId;
  final Media media;
  final List<String> originCountry;
  final Person person;

  PersonCredit({
    @required this.creditId,
    @required this.media,
    @required this.person,
    this.originCountry,
  })  : assert(creditId != null),
        assert(media != null),
        assert(person != null),
        super();

  @override
  String toString() {
    return person.toString();
  }

  /// Creates a [PersonCredit] from a JSON object.
  factory PersonCredit.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['origin_country'] as List;
    List<String> countries = list?.map((i) => i.toString())?.toList();

    return PersonCredit(
      creditId: json['credit_id'],
      media: Media.fromJsonType(json),
      originCountry: countries,
      person: Person.fromJson(json),
    );
  }

  String title() {
    return media.getTitle();
  }
}
