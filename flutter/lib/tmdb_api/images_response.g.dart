// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'images_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ImagesResponse _$ImagesResponseFromJson(Map<String, dynamic> json) {
  return ImagesResponse(
    id: json['id'] as int?,
    backdrops: (json['backdrops'] == null)
        ? []
        : (json['backdrops'] as List)
            .nonNulls
            .map((e) => MovieImage.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    posters: (json['posters'] == null)
        ? []
        : (json['posters'] as List)
            .nonNulls
            .map((e) => MovieImage.fromJson(e as Map<String, dynamic>)!)
            .toList(),
  );
}
