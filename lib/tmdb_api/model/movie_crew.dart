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
          backdropPath: credit.backdropPath,
          creditId: credit.creditId,
          genreIds: credit.genreIds,
          movie: credit.movie,
          originCountry: credit.originCountry,
          originalLanguage: credit.originalLanguage,
          originalName: credit.originalName,
          originalTitle: credit.originalTitle,
          overview: credit.overview,
          person: credit.person,
          posterPath: credit.posterPath,
          releaseDate: credit.releaseDate,
          title: credit.title,
          video: credit.video,
          voteAverage: credit.voteAverage,
          voteCount: credit.voteCount,
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
