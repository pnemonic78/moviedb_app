import 'package:flutter/foundation.dart';

import 'person_credit.dart';

class PersonCrew extends PersonCredit {
  final PersonCredit credit;
  final String department;
  final String job;

  PersonCrew({
    @required this.credit,
    this.department,
    @required this.job,
  })  : assert(job != null),
        super(
          backdropPath: credit.backdropPath,
          creditId: credit.creditId,
          media: credit.media,
          episodeCount: credit.episodeCount,
          firstAirDate: credit.firstAirDate,
          genreIds: credit.genreIds,
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

  /// Creates a [PersonCrew] from a JSON object.
  factory PersonCrew.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return PersonCrew(
      credit: PersonCredit.fromJson(json),
      department: json['department'],
      job: json['job'],
    );
  }
}
