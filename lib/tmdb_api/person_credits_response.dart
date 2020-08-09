import 'model/person_cast.dart';
import 'model/person_crew.dart';

class PersonCreditsResponse {
  int id;
  List<PersonCast> cast;
  List<PersonCrew> crew;

  PersonCreditsResponse({
    this.id,
    this.cast,
    this.crew,
  });

  /// Creates a [PersonCreditsResponse] from a JSON object.
  factory PersonCreditsResponse.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['cast'] as List;
    List<PersonCast> cast = list?.map((i) => PersonCast.fromJson(i))?.toList();

    list = json['crew'] as List;
    List<PersonCrew> crew = list?.map((i) => PersonCrew.fromJson(i))?.toList();

    return PersonCreditsResponse(
      id: json['id'],
      cast: cast,
      crew: crew,
    );
  }
}
