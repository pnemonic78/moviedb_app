import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/model/media.dart';

import 'date_converter.dart';
import 'media_type.dart';

part 'tv.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieDateTimeConverter()
class Television extends Media {
  @JsonKey(name: 'episode_count')
  int episodeCount;
  @JsonKey(name: 'first_air_date')
  DateTime firstAirDate;

  Television({
    this.episodeCount,
    this.firstAirDate,
    @required Media media,
  })  : super(
          adult: media?.adult,
          id: media?.id,
          mediaType: media?.mediaType ?? MediaType.tv,
          popularity: media?.popularity,
        );

  /// Creates a [Television] from a JSON object.
  factory Television.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$TelevisionFromJson(json);

  @override
  DateTime date() {
    return firstAirDate;
  }
}
