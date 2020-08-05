import 'model/cast.dart';
import 'model/crew.dart';

class PersonCreditsResponse {
  final int id;
  final List<MovieCast> cast;
  final List<MovieCrew> crew;

  const PersonCreditsResponse({this.id, this.cast, this.crew});

  /// Creates a [PersonCreditsResponse] from a JSON object.
  factory PersonCreditsResponse.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['cast'] as List;
    List<MovieCast> cast = list?.map((i) => MovieCast.fromJson(i))?.toList();
    list = json['crew'] as List;
    List<MovieCrew> crew = list?.map((i) => MovieCrew.fromJson(i))?.toList();

    return PersonCreditsResponse(
      id: json['id'],
      cast: cast,
      crew: crew,
    );
  }
}
