// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'movie_details.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MovieDetails _$MovieDetailsFromJson(Map<String, dynamic> json) {
  return MovieDetails(
    budget: json['budget'] as int,
    genres: (json['genres'] as List)
        ?.map(
            (e) => e == null ? null : Genre.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    homepage: json['homepage'] as String,
    imdbId: json['imdb_id'] as String,
    productionCompanies: (json['production_companies'] as List)
        ?.map((e) => e == null
            ? null
            : ProductionCompany.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    productionCountries: (json['production_countries'] as List)
        ?.map((e) => e == null
            ? null
            : ProductionCountry.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    revenue: json['revenue'] as int,
    runtime: json['runtime'] as int,
    spokenLanguages: (json['spoken_languages'] as List)
        ?.map((e) => e == null
            ? null
            : SpokenLanguage.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    status: json['status'] as String,
    tagline: json['tagline'] as String,
    credits: json['credits'] == null
        ? null
        : CreditsResponse.fromJson(json['credits'] as Map<String, dynamic>),
  )
    ..adult = json['adult'] as bool
    ..id = json['id'] as int
    ..mediaType = _$enumDecodeNullable(_$MediaTypeEnumMap, json['media_type'])
    ..popularity = (json['popularity'] as num)?.toDouble()
    ..backdropPath = json['backdrop_path'] as String
    ..genreIds = (json['genre_ids'] as List)?.map((e) => e as int)?.toList()
    ..originCountry =
        (json['origin_country'] as List)?.map((e) => e as String)?.toList()
    ..originalLanguage = json['original_language'] as String
    ..originalTitle = json['original_title'] as String
    ..overview = json['overview'] as String
    ..posterPath = json['poster_path'] as String
    ..releaseDate =
        const MovieDateTimeConverter().fromJson(json['release_date'] as String)
    ..title = json['title'] as String
    ..video = json['video'] as bool
    ..voteAverage = (json['vote_average'] as num)?.toDouble()
    ..voteCount = json['vote_count'] as int;
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
