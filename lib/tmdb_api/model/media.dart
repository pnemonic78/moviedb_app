import 'package:flutter/foundation.dart';
import 'package:tmdb/tmdb_api/model/tv.dart';

import 'media_type.dart';
import 'movie.dart';
import 'person.dart';

class Media {
  final bool adult;
  final int id;
  final MediaType mediaType;
  final double popularity;

  Media({
    this.adult,
    @required this.id,
    this.mediaType,
    this.popularity,
  }) : assert(id != null);

  /// Creates a [Media] from a JSON object.
  factory Media.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return Media(
      adult: json['adult'],
      id: json['id'],
      mediaType: MediaType.fromJson(json['media_type']),
      popularity: json['popularity']?.toDouble(),
    );
  }

  factory Media.fromJsonType(Map<String, dynamic> json) {
    if (json == null) return null;

    final mediaType = MediaType.fromJson(json['media_type']) ?? MediaType.all;

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
}
