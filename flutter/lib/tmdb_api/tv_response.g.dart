// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'tv_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TelevisionResponse _$TelevisionResponseFromJson(Map<String, dynamic> json) {
  return TelevisionResponse(
    results: (json['results'] == null)
        ? []
        : (json['results'] as List)
            .nonNulls
            .map((e) => Television.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    page: json['page'] as int,
    totalPages: json['total_pages'] as int,
    totalResults: json['total_results'] as int,
  );
}
