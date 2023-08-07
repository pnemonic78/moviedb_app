// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'image.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MovieImage _$MovieImageFromJson(Map<String, dynamic> json) {
  return MovieImage(
    id: json['id'] as String,
    locale: const MovieLocaleConverter().fromJson(json['iso_639_1'] as String?),
    width: json['width'] as int,
    height: json['height'] as int,
    aspectRatio: (json['aspect_ratio'] as num?)?.toDouble() ?? 1,
    path: json['file_path'] as String,
    voteAverage: (json['vote_average'] as num?)?.toDouble() ?? 0,
    voteCount: (json['vote_count'] as int?) ?? 0,
  );
}
