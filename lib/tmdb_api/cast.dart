import 'package:tmdb/tmdb_api/credit.dart';

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
    return '{id: $id, name: "$name, character: $character"}';
  }

  /// Creates a [MovieCast] from a JSON object.
  factory MovieCast.fromJson(Map<String, dynamic> json) {
    return MovieCast(
      json['id'],
      json['name'],
      json['credit_id'],
      json['cast_id'],
      profilePath: json['profile_path'],
      gender: json['gender'],

      // cast
      character: json['character'],
      order: json['order'],
    );
  }
}
