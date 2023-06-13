// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'videos_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

VideosResponse _$VideosResponseFromJson(Map<String, dynamic> json) {
  return VideosResponse(
    id: json['id'] as int,
    results: (json['results'] == null)
        ? []
        : (json['results'] as List)
            .nonNulls
            .map((e) => MovieVideo.fromJson(e as Map<String, dynamic>)!)
            .toList(),
  );
}
