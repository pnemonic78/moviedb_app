import 'package:json_annotation/json_annotation.dart';

import 'model/video.dart';

part 'videos_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class VideosResponse {
  @JsonKey(name: 'id')
  int id;
  @JsonKey(name: 'results')
  List<MovieVideo> results;

  VideosResponse({
    required this.id,
    this.results = const [],
  });

  /// Creates a [VideosResponse] from a JSON object.
  static VideosResponse? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$VideosResponseFromJson(json);
}
