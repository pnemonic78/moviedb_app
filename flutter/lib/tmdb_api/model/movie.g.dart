// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'movie.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Movie _$MovieFromJson(Map<String, dynamic> json) {
  return Movie(
    media: Media.fromJson(json)!,
    backdropPath: json['backdrop_path'] as String?,
    genreIds: (json['genre_ids'] == null)
        ? []
        : (json['genre_ids'] as List).nonNulls.map((e) => e as int).toList(),
    originCountry: (json['origin_country'] == null)
        ? []
        : (json['origin_country'] as List)
            .nonNulls
            .map((e) => e as String)
            .toList(),
    originalLanguage: json['original_language'] as String?,
    originalTitle: json['original_title'] as String?,
    overview: json['overview'] as String?,
    posterPath: json['poster_path'] as String?,
    releaseDate: const MovieDateTimeConverter()
        .fromJson(json['release_date'] as String?),
    title: json['title'] as String,
    video: (json['video'] as bool?) ?? false,
    voteAverage: (json['vote_average'] as num?)?.toDouble() ?? 0,
    voteCount: (json['vote_count'] as int?) ?? 0,
  );
}
