// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'media_credit.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MediaCredit _$MediaCreditFromJson(Map<String, dynamic> json) {
  return MediaCredit(
    person: Person.fromJson(json)!,
    creditId: json['credit_id'] as String,
    media: Media.fromJson(json)!,
  );
}
