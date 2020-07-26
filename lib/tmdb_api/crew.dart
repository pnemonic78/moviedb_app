import 'package:tmdb/tmdb_api/credit.dart';

class MovieCrew extends MovieCredit {
  final String department;
  final String job;

  const MovieCrew(
      {int id,
      this.department,
      this.job,
      String creditId,
      String name,
      String profilePath,
      int gender})
      : super(
          id: id,
          creditId: creditId,
          name: name,
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
      id: json['id'],
      creditId: json['credit_id'],
      name: json['name'],
      profilePath: json['profile_path'],
      gender: json['gender'],

      // crew
      department: json['department'],
      job: json['job'],
    );
  }
}
