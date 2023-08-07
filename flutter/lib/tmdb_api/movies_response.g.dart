// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'movies_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MoviesResponse _$MoviesResponseFromJson(Map<String, dynamic> json) {
  return MoviesResponse(
    results: (json['results'] == null)
        ? []
        : (json['results'] as List)
            .nonNulls
            .map((e) => Movie.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    dates: json['dates'] == null
        ? null
        : Dates.fromJson(json['dates'] as Map<String, dynamic>),
    page: json['page'] as int,
    totalPages: json['total_pages'] as int,
    totalResults: json['total_results'] as int,
  );
}
