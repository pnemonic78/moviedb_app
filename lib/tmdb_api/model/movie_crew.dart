import 'package:flutter/foundation.dart';

import 'movie_credit.dart';

class MovieCrew extends MovieCredit {
  final MovieCredit credit;
  final String department;
  final String job;

  MovieCrew({
    @required this.credit,
    this.department,
    @required this.job,
  })  : assert(job != null),
        super(
          creditId: credit.creditId,
          movie: credit.movie,
          originCountry: credit.originCountry,
          person: credit.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", department: "$department", job: "$job"}';
  }

  /// Creates a [MovieCrew] from a JSON object.
  factory MovieCrew.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return MovieCrew(
      credit: MovieCredit.fromJson(json),
      department: json['department'],
      job: json['job'],
    );
  }
}
