// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'person_crew.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PersonCrew _$PersonCrewFromJson(Map<String, dynamic> json, Person person) {
  return PersonCrew(
    credit: PersonCredit.fromJson(json, person)!,
    department: json['department'] as String,
    job: json['job'] as String,
  );
}
