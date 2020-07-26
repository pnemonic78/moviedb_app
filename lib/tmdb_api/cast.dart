import 'package:tmdb/tmdb_api/credit.dart';

import 'gender.dart';

class MovieCast extends MovieCredit {
  final int castId;
  final String character;
  final int order;

  const MovieCast(
    int id,
    String name,
    String creditId,
    this.castId, {
    this.character,
    this.order,
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
    return '{id: $id, name: "$name, character: $character"}';
  }

  /// Creates a [MovieCast] from a JSON object.
  factory MovieCast.fromJson(Map<String, dynamic> json) {
    final gender = Gender_fromJson(json['gender']);

    return MovieCast(
      json['id'],
      json['name'],
      json['credit_id'],
      json['cast_id'],
      profilePath: json['profile_path'],
      gender: gender,

      // cast
      character: json['character'],
      order: json['order'],
    );
  }
}
