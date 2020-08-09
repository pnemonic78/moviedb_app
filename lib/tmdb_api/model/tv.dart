import 'package:flutter/foundation.dart';
import 'package:tmdb/tmdb_api/model/media.dart';

import 'dates.dart';
import 'media_type.dart';

class Television extends Media {
  int episodeCount;
  DateTime firstAirDate;
  Media media;

  Television({
    this.episodeCount,
    this.firstAirDate,
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
      episodeCount: json['episode_count'],
      firstAirDate: parseDateTime(json['first_air_date']),
      media: Media.fromJson(json),
    );
  }

  @override
  DateTime date() {
    return firstAirDate;
  }
}
