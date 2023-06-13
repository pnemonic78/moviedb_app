// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'tv.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Television _$TelevisionFromJson(Map<String, dynamic> json) {
  return Television(
    media: Media.fromJson(json)!,
    episodeCount: (json['episode_count'] as int?) ?? 0,
    firstAirDate: const MovieDateTimeConverter()
        .fromJson(json['first_air_date'] as String?),
  );
}
