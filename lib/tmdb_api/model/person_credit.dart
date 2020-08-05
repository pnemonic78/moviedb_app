import 'package:flutter/foundation.dart';

import 'media.dart';
import 'person.dart';

/// A [Person] that belongs to a [Media].
class PersonCredit {
  final String creditId;
  final Media media;
  final Person person;

  PersonCredit({
    @required this.creditId,
    @required this.media,
    @required this.person,
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

    return PersonCredit(
      creditId: json['credit_id'],
      media: Media.fromJsonType(json),
      person: Person.fromJson(json),
    );
  }

  String title() {
    return media.getTitle();
  }
}
