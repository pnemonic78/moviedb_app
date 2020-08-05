import 'package:flutter/foundation.dart';
import 'package:tmdb/tmdb_api/model/media.dart';

import 'media_type.dart';

class Television extends Media {
  final Media media;

  Television({
    @required this.media,
  })  : assert(media != null),
        super(
          adult: media.adult,
          id: media.id,
          mediaType: media.mediaType ?? MediaType.tv,
          popularity: media.popularity,
        );

  /// Creates a [Television] from a JSON object.
  factory Television.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return Television(
      media: Media.fromJson(json),
    );
  }
}
