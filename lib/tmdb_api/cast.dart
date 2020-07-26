import 'package:tmdb/tmdb_api/credit.dart';

class MovieCast extends MovieCredit {
  final int castId;
  final String character;
  final int order;

  const MovieCast(
      {int id,
      this.castId,
      this.character,
      this.order,
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
    return '{id: $id, name: "$name, character: $character"}';
  }

  /// Creates a [MovieCast] from a JSON object.
  factory MovieCast.fromJson(Map<String, dynamic> json) {
    return MovieCast(
      // credit
      id: json['id'],
      creditId: json['credit_id'],
      name: json['name'],
      profilePath: json['profile_path'],
      gender: json['gender'],

      // cast
      castId: json['cast_id'],
      character: json['character'],
      order: json['order'],
    );
  }
}
