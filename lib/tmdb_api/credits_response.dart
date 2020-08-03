import 'model/cast.dart';
import 'model/crew.dart';

class CreditsResponse {
  final int id;
  final List<MovieCast> cast;
  final List<MovieCrew> crew;

  const CreditsResponse({this.id, this.cast, this.crew});

  /// Creates a [CreditsResponse] from a JSON object.
  factory CreditsResponse.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['cast'] as List;
    List<MovieCast> cast = list.map((i) => MovieCast.fromJson(i)).toList();
    list = json['crew'] as List;
    List<MovieCrew> crew = list.map((i) => MovieCrew.fromJson(i)).toList();

    return CreditsResponse(
      id: json['id'],
      cast: cast,
      crew: crew,
    );
  }
}
