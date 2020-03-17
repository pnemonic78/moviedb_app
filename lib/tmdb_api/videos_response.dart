import 'video.dart';

class VideosResponse {
  final int id;
  final List<Video> results;

  const VideosResponse({this.id, this.results});

  /// Creates a [VideosResponse] from a JSON object.
  factory VideosResponse.fromJson(Map<String, dynamic> json) {
    var list = json['results'] as List;
    List<Video> results = list.map((j) => Video.fromJson(j)).toList();

    return VideosResponse(
      id: json['id'],
      results: results,
    );
  }
}
