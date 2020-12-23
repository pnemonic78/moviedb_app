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
    this.id,
    this.results,
  });

  /// Creates a [VideosResponse] from a JSON object.
  factory VideosResponse.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$VideosResponseFromJson(json);
}
