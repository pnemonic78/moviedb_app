// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'media_cast.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MediaCast _$MediaCastFromJson(Map<String, dynamic> json) {
  return MediaCast(
    credit: MediaCredit.fromJson(json)!,
    castId: json['cast_id'] as int,
    character: json['character'] as String,
    order: (json['order'] as int?) ?? 0,
  );
}
