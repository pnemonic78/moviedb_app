// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'credits_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CreditsResponse _$CreditsResponseFromJson(Map<String, dynamic> json) {
  return CreditsResponse(
    id: json['id'] as int,
    cast: (json['cast'] == null)
        ? []
        : (json['cast'] as List)
            .nonNulls
            .map((e) => MediaCast.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    crew: (json['crew'] == null)
        ? []
        : (json['crew'] as List)
            .nonNulls
            .map((e) => MediaCrew.fromJson(e as Map<String, dynamic>)!)
            .toList(),
  );
}
