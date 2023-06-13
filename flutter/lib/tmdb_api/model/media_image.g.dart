// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'media_image.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MediaImage _$MediaImageFromJson(Map<String, dynamic> json) {
  return MediaImage(
    id: json['id'] as String,
    locale: const MovieLocaleConverter().fromJson(json['iso_639_1'] as String?),
    key: json['key'] as String,
    name: json['name'] as String,
    site: json['site'] as String,
    size: json['size'] as int,
    type: json['type'] as String,
  );
}
