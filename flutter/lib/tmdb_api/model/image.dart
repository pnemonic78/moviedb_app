import 'dart:ui';

import 'package:json_annotation/json_annotation.dart';

import 'locale_converter.dart';

part 'image.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieLocaleConverter()
class MovieImage {
  @JsonKey(name: 'id')
  String id;
  @JsonKey(name: 'iso_639_1')
  Locale locale;
  @JsonKey(name: 'width')
  int width;
  @JsonKey(name: 'height')
  int height;
  @JsonKey(name: 'aspect_ratio')
  double aspectRatio;
  @JsonKey(name: 'file_path')
  String path;
  @JsonKey(name: 'vote_average')
  double voteAverage;
  @JsonKey(name: 'vote_count')
  int voteCount;

  MovieImage({
    this.id,
    this.locale,
    this.width,
    this.height,
    this.aspectRatio,
    this.path,
    this.voteAverage,
    this.voteCount,
  });

  @override
  String toString() {
    return '{id: $id, path: "$path", size: $width x $height}';
  }

  /// Creates a [MovieImage] from a JSON object.
  factory MovieImage.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$MovieImageFromJson(json)
        ..locale = MovieLocaleConverter.fromJsons(json);
}
