import 'dart:ui';

import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/locale_converter.dart';

part 'media_image.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieLocaleConverter()
class MediaImage {
  @JsonKey(name: 'id')
  String id;
  @JsonKey(name: 'iso_639_1')
  Locale? locale;
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

  MediaImage({
    required this.id,
    this.locale,
    required this.key,
    required this.name,
    required this.site,
    required this.size,
    required this.type,
  });

  @override
  String toString() {
    return '{id: $id, name: "$name", type: $type}';
  }

  /// Creates a [MediaImage] from a JSON object.
  static MediaImage? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$MediaImageFromJson(json)
        ?..locale = MovieLocaleConverter.fromJsonMap(json);
}
