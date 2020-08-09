// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'images_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ImagesResponse _$ImagesResponseFromJson(Map<String, dynamic> json) {
  return ImagesResponse(
    id: json['id'] as int,
    backdrops: (json['backdrops'] as List)
        ?.map((e) =>
            e == null ? null : MovieImage.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    posters: (json['posters'] as List)
        ?.map((e) =>
            e == null ? null : MovieImage.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}
