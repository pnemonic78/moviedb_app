import 'package:flutter/foundation.dart';

import 'media_type.dart';

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
}
