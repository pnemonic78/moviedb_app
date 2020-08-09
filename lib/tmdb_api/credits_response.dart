import 'model/media_cast.dart';
import 'model/media_crew.dart';

class CreditsResponse {
  int id;
  List<MediaCast> cast;
  List<MediaCrew> crew;

  CreditsResponse({
    this.id,
    this.cast,
    this.crew,
  });

  /// Creates a [CreditsResponse] from a JSON object.
  factory CreditsResponse.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['cast'] as List;
    List<MediaCast> cast = list?.map((i) => MediaCast.fromJson(i))?.toList();

    list = json['crew'] as List;
    List<MediaCrew> crew = list?.map((i) => MediaCrew.fromJson(i))?.toList();

    return CreditsResponse(
      id: json['id'],
      cast: cast,
      crew: crew,
    );
  }
}
