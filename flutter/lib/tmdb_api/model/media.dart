import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/model/tv.dart';

import 'media_type.dart';
import 'movie.dart';
import 'person.dart';

part 'media.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class Media {
  @JsonKey(name: 'adult')
  bool adult;
  @JsonKey(name: 'id')
  int id;
  @JsonKey(name: 'media_type')
  MediaType? mediaType;
  @JsonKey(name: 'popularity')
  double popularity;

  Media({
    this.adult = false,
    required this.id,
    this.mediaType,
    this.popularity = 0,
  });

  /// Creates a [Media] from a JSON object.
  static Media? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$MediaFromJson(json);

  static Media? fromJsonType(Map<String, dynamic>? json) {
    if (json == null) return null;

    final mediaType =  _$enumDecodeNullable(_$MediaTypeEnumMap, json['media_type']) ?? MediaType.all;

    switch (mediaType) {
      case MediaType.movie:
        return Movie.fromJson(json);
      case MediaType.person:
        return Person.fromJson(json);
      case MediaType.tv:
        return Television.fromJson(json);
      default:
        return Media.fromJson(json);
    }
  }

  DateTime? date() {
    return null;
  }

  String? getTitle() {
    return null;
  }
}
