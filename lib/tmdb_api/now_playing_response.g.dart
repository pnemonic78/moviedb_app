// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'now_playing_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MoviesNowPlayingResponse _$MoviesNowPlayingResponseFromJson(
    Map<String, dynamic> json) {
  return MoviesNowPlayingResponse(
    results: (json['results'] as List)
        ?.map(
            (e) => e == null ? null : Movie.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    dates: json['dates'] == null
        ? null
        : Dates.fromJson(json['dates'] as Map<String, dynamic>),
    page: json['page'] as int,
    totalPages: json['total_pages'] as int,
    totalResults: json['total_results'] as int,
  );
}
