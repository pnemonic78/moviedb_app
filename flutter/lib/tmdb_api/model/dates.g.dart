// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'dates.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Dates _$DatesFromJson(Map<String, dynamic> json) {
  return Dates(
    minimum: json['minimum'] == null
        ? null
        : DateTime.parse(json['minimum'] as String),
    maximum: json['maximum'] == null
        ? null
        : DateTime.parse(json['maximum'] as String),
  );
}
