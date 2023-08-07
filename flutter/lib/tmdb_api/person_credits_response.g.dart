// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'person_credits_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PersonCreditsResponse _$PersonCreditsResponseFromJson(
    Map<String, dynamic> json, Person person) {
  return PersonCreditsResponse(
    id: json['id'] as int?,
    cast: (json['cast'] == null)
        ? []
        : (json['cast'] as List)
            .nonNulls
            .map((e) => PersonCast.fromJson(e as Map<String, dynamic>, person)!)
            .toList(),
    crew: (json['crew'] == null)
        ? []
        : (json['crew'] as List)
            .nonNulls
            .map((e) => PersonCrew.fromJson(e as Map<String, dynamic>, person)!)
            .toList(),
  );
}
