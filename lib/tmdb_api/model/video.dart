import 'dart:ui';

import 'package:json_annotation/json_annotation.dart';

import 'locale_converter.dart';

part 'video.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieLocaleConverter()
class MovieVideo {
  @JsonKey(name: 'id')
  String id;
  @JsonKey(name: 'iso_639_1')
  Locale locale;
  @JsonKey(name: 'key')
  String key;
  @JsonKey(name: 'name')
  String name;
  @JsonKey(name: 'site')
  String site;
  @JsonKey(name: 'size')
  int size;
  @JsonKey(name: 'type')
  String type;

  MovieVideo({
    this.id,
    this.locale,
    this.key,
    this.name,
    this.site,
    this.size,
    this.type,
  });

  @override
  String toString() {
    return '{id: $id, name: "$name", type: $type}';
  }

  /// Creates a [MovieVideo] from a JSON object.
  factory MovieVideo.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$MovieVideoFromJson(json)
        ..locale = MovieLocaleConverter.fromJsons(json);
}
