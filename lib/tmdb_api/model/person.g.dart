// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'person.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Person _$PersonFromJson(Map<String, dynamic> json) {
  return Person(
    aliases: (json['aliases'] as List)?.map((e) => e as String)?.toList(),
    biography: json['biography'] as String,
    birthday: json['birthday'] == null
        ? null
        : DateTime.parse(json['birthday'] as String),
    birthplace: json['birthplace'] as String,
    credits: json['credits'] == null
        ? null
        : PersonCreditsResponse.fromJson(
            json['credits'] as Map<String, dynamic>),
    deathday: json['deathday'] == null
        ? null
        : DateTime.parse(json['deathday'] as String),
    externalIds: json['externalIds'] == null
        ? null
        : PersonExternalIds.fromJson(
            json['externalIds'] as Map<String, dynamic>),
    gender: _$enumDecodeNullable(_$GenderEnumMap, json['gender']),
    homepage: json['homepage'] as String,
    imdbId: json['imdbId'] as String,
    knownDepartment: json['knownDepartment'] as String,
    name: json['name'] as String,
    originalName: json['originalName'] as String,
    profilePath: json['profilePath'] as String,
  )
    ..adult = json['adult'] as bool
    ..id = json['id'] as int
    ..mediaType = _$enumDecodeNullable(_$MediaTypeEnumMap, json['media_type'])
    ..popularity = (json['popularity'] as num)?.toDouble();
}

T _$enumDecode<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T unknownValue,
}) {
  if (source == null) {
    throw ArgumentError('A value must be provided. Supported values: '
        '${enumValues.values.join(', ')}');
  }

  final value = enumValues.entries
      .singleWhere((e) => e.value == source, orElse: () => null)
      ?.key;

  if (value == null && unknownValue == null) {
    throw ArgumentError('`$source` is not one of the supported values: '
        '${enumValues.values.join(', ')}');
  }
  return value ?? unknownValue;
}

T _$enumDecodeNullable<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T unknownValue,
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

const _$MediaTypeEnumMap = {
  MediaType.all: 'all',
  MediaType.movie: 'movie',
  MediaType.tv: 'tv',
  MediaType.person: 'person',
};
