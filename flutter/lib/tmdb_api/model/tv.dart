import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/date_converter.dart';
import 'package:tmdb/tmdb_api/model/media.dart';

part 'tv.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieDateTimeConverter()
class Television extends Media {
  @JsonKey(name: 'episode_count')
  int episodeCount;
  @JsonKey(name: 'first_air_date')
  DateTime? firstAirDate;

  Television({
    required Media media,
    this.episodeCount = 0,
    this.firstAirDate,
  }) : super(
          adult: media.adult,
          id: media.id,
          mediaType: media.mediaType,
          popularity: media.popularity,
        );

  /// Creates a [Television] from a JSON object.
  static Television? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$TelevisionFromJson(json);

  @override
  DateTime? date() {
    return firstAirDate;
  }
}
