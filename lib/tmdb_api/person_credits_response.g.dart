// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'person_credits_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PersonCreditsResponse _$PersonCreditsResponseFromJson(
    Map<String, dynamic> json) {
  return PersonCreditsResponse(
    id: json['id'] as int,
    cast: (json['cast'] as List)
        ?.map((e) =>
            e == null ? null : PersonCast.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    crew: (json['crew'] as List)
        ?.map((e) =>
            e == null ? null : PersonCrew.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}
