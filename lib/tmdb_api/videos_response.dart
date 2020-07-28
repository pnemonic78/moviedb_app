import 'model/video.dart';

class VideosResponse {
  final int id;
  final List<MovieVideo> results;

  const VideosResponse({this.id, this.results});

  /// Creates a [VideosResponse] from a JSON object.
  factory VideosResponse.fromJson(Map<String, dynamic> json) {
    var list = json['results'] as List;
    List<MovieVideo> results = list.map((i) => MovieVideo.fromJson(i)).toList();

    return VideosResponse(
      id: json['id'],
      results: results,
    );
  }
}
