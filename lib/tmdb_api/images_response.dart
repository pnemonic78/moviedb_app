import 'package:json_annotation/json_annotation.dart';

import 'model/image.dart';

part 'images_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class ImagesResponse {
  @JsonKey(name: 'id')
  int id;
  @JsonKey(name: 'backdrops')
  List<MovieImage> backdrops;
  @JsonKey(name: 'posters')
  List<MovieImage> posters;

  ImagesResponse({
    this.id,
    this.backdrops,
    this.posters,
  });

  /// Creates a [ImagesResponse] from a JSON object.
  factory ImagesResponse.fromJson(Map<String, dynamic> json) =>
      (json != null) ? _$ImagesResponseFromJson(json) : null;
}
