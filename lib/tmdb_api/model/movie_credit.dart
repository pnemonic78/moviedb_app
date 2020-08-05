import 'package:flutter/foundation.dart';

import 'movie.dart';
import 'person.dart';

class MovieCredit {
  final String creditId;
  final Movie movie;
  final List<String> originCountry;
  final Person person;

  MovieCredit({
    @required this.creditId,
    @required this.movie,
    this.originCountry,
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

    var list = json['origin_country'] as List;
    List<String> countries = list?.map((i) => i.toString())?.toList();

    return MovieCredit(
      creditId: json['credit_id'],
      movie: Movie.fromJson(json),
      originCountry: countries,
      person: Person.fromJson(json),
    );
  }
}
