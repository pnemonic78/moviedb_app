import 'package:flutter/foundation.dart';
import 'package:tmdb/tmdb_api/model/media.dart';

import 'person.dart';

/// A [Person] credit that belongs to a [Media].
class MediaCredit {
  String creditId;
  Media media;
  Person person;

  MediaCredit({
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

  /// Creates a [MediaCredit] from a JSON object.
  factory MediaCredit.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return MediaCredit(
      creditId: json['credit_id'],
      media: Media.fromJsonType(json),
      person: Person.fromJson(json),
    );
  }
}
