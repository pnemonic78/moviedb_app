import 'package:flutter/foundation.dart';

import 'movie.dart';
import 'person.dart';

/// A [Person] credit that belongs to a [Media].
class MediaCredit {
  final String creditId;
  final Movie movie;
  final Person person;

  MediaCredit({
    @required this.creditId,
    @required this.movie,
    @required this.person,
  })  : assert(creditId != null),
        assert(movie != null),
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
      movie: Movie.fromJson(json),
      person: Person.fromJson(json),
    );
  }
}
