// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'person_credit.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PersonCredit _$PersonCreditFromJson(Map<String, dynamic> json, Person person) {
  return PersonCredit(
    creditId: json['credit_id'] as String?,
    media: Media.fromJsonType(json)!,
    person: person,
  );
}
