import 'package:tmdb/tmdb_api/credit.dart';

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
    int gender,
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
    return MovieCrew(
      // credit
      json['id'],
      json['name'],
      json['credit_id'],
      profilePath: json['profile_path'],
      gender: json['gender'],

      // crew
      department: json['department'],
      job: json['job'],
    );
  }
}
