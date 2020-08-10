// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'media_crew.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MediaCrew _$MediaCrewFromJson(Map<String, dynamic> json) {
  return MediaCrew(
    department: json['department'] as String,
    job: json['job'] as String,
  )
    ..adult = json['adult'] as bool
    ..id = json['id'] as int
    ..mediaType = _$enumDecodeNullable(_$MediaTypeEnumMap, json['media_type'])
    ..popularity = (json['popularity'] as num)?.toDouble()
    ..aliases =
        (json['also_known_as'] as List)?.map((e) => e as String)?.toList()
    ..biography = json['biography'] as String
    ..birthday = json['birthday'] == null
        ? null
        : DateTime.parse(json['birthday'] as String)
    ..credits = json['combined_credits'] == null
        ? null
        : PersonCreditsResponse.fromJson(
            json['combined_credits'] as Map<String, dynamic>)
    ..birthplace = json['place_of_birth'] as String
    ..deathday = json['deathday'] == null
        ? null
        : DateTime.parse(json['deathday'] as String)
    ..externalIds = json['external_ids'] == null
        ? null
        : PersonExternalIds.fromJson(
            json['external_ids'] as Map<String, dynamic>)
    ..gender = _$enumDecodeNullable(_$GenderEnumMap, json['gender'])
    ..homepage = json['homepage'] as String
    ..imdbId = json['imdb_id'] as String
    ..knownDepartment = json['known_for_department'] as String
    ..name = json['name'] as String
    ..originalName = json['original_name'] as String
    ..profilePath = json['profile_path'] as String
    ..creditId = json['credit_id'] as String;
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

const _$MediaTypeEnumMap = {
  MediaType.all: 'all',
  MediaType.movie: 'movie',
  MediaType.tv: 'tv',
  MediaType.person: 'person',
};

const _$GenderEnumMap = {
  Gender.unknown: 0,
  Gender.female: 1,
  Gender.male: 2,
};
