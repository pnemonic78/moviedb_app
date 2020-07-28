import 'package:tmdb/tmdb_api/model/credit.dart';

import 'gender.dart';

class MovieCrew extends MovieCredit {
  final String department;
  final String job;

  const MovieCrew(
    int id,
    String name,
    String creditId, {
    this.department,
    this.job,
    String profilePath,
    Gender gender,
  }) : super(
          id,
          name,
          creditId,
          profilePath: profilePath,
          gender: gender,
        );

  @override
  String toString() {
    return '{id: $id, name: "$name, job: $job"}';
  }

  /// Creates a [MovieCrew] from a JSON object.
  factory MovieCrew.fromJson(Map<String, dynamic> json) {
    final gender = Gender_fromJson(json['gender']);

    return MovieCrew(
      json['id'],
      json['name'],
      json['credit_id'],
      profilePath: json['profile_path'],
      gender: gender,

      // crew
      department: json['department'],
      job: json['job'],
    );
  }
}
