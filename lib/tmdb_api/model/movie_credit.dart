import 'package:flutter/foundation.dart';

import 'movie.dart';
import 'person.dart';

/// A [Media] with a [Person].
class MovieCredit {
  final String creditId;
  final Movie movie;
  final Person person;

  MovieCredit({
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

  /// Creates a [MovieCredit] from a JSON object.
  factory MovieCredit.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return MovieCredit(
      creditId: json['credit_id'],
      movie: Movie.fromJson(json),
      person: Person.fromJson(json),
    );
  }
}
