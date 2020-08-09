// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'tv.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Television _$TelevisionFromJson(Map<String, dynamic> json) {
  return Television(
    episodeCount: json['episode_count'] as int,
    firstAirDate: parseDateTime(json['first_air_date']),
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

const _$MediaTypeEnumMap = {
  MediaType.all: 'all',
  MediaType.movie: 'movie',
  MediaType.tv: 'tv',
  MediaType.person: 'person',
};
