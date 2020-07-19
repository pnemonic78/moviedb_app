import 'video.dart';

class VideosResponse {
  final int id;
  final List<MovieVideo> results;

  const VideosResponse({this.id, this.results});

  /// Creates a [VideosResponse] from a JSON object.
  factory VideosResponse.fromJson(Map<String, dynamic> json) {
    var list = json['results'] as List;
    List<MovieVideo> results = list.map((j) => MovieVideo.fromJson(j)).toList();

    return VideosResponse(
      id: json['id'],
      results: results,
    );
  }
}
