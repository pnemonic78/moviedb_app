// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'person.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Person _$PersonFromJson(Map<String, dynamic> json) {
  return Person(
    media: Media.fromJson(json)!,
    aliases: (json['also_known_as'] == null)
        ? []
        : (json['also_known_as'] as List)
            .nonNulls
            .map((e) => e as String)
            .toList(),
    biography: json['biography'] as String?,
    birthday:
        const MovieDateTimeConverter().fromJson(json['birthday'] as String?),
    birthplace: json['place_of_birth'] as String?,
    credits: json['combined_credits'] == null
        ? null
        : PersonCreditsResponse.fromJson(
            json['combined_credits'] as Map<String, dynamic>?),
    deathday:
        const MovieDateTimeConverter().fromJson(json['deathday'] as String?),
    externalIds: json['external_ids'] == null
        ? null
        : PersonExternalIds.fromJson(
            json['external_ids'] as Map<String, dynamic>?),
    gender: _$enumDecodeNullable(_$GenderEnumMap, json['gender']),
    homepage: json['homepage'] as String?,
    imdbId: json['imdb_id'] as String?,
    knownDepartment: json['known_for_department'] as String?,
    name: json['name'] as String,
    originalName: json['original_name'] as String?,
    profilePath: json['profile_path'] as String?,
  );
}

T _$enumDecode<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T? unknownValue,
}) {
  if (source == null) {
    throw ArgumentError('A value must be provided. Supported values: '
        '${enumValues.values.join(', ')}');
  }

  final value = enumValues.entries.singleWhere((e) => e.value == source)?.key;

  if (value == null && unknownValue == null) {
    throw ArgumentError('`$source` is not one of the supported values: '
        '${enumValues.values.join(', ')}');
  }
  return value ?? unknownValue!;
}

T? _$enumDecodeNullable<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T? unknownValue,
}) {
  if (source == null) {
    return null;
  }
  return _$enumDecode<T>(enumValues, source, unknownValue: unknownValue);
}

const _$GenderEnumMap = {
  Gender.unknown: 0,
  Gender.female: 1,
  Gender.male: 2,
};
