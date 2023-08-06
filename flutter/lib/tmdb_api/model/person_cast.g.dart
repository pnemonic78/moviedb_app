// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'person_cast.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PersonCast _$PersonCastFromJson(Map<String, dynamic> json, Person person) {
  return PersonCast(
    credit: PersonCredit.fromJson(json, person)!,
    castId: json['cast_id'] as int?,
    character: json['character'] as String,
    order: (json['order'] as int?) ?? 0,
  );
}
