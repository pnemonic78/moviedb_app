import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';

import 'model/dates.dart';
import 'model/movie.dart';

part 'movies_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class MoviesResponse {
  @JsonKey(name: 'results')
  List<Movie> results;
  @JsonKey(name: 'dates')
  Dates dates;
  @JsonKey(name: 'page')
  int page;
  @JsonKey(name: 'total_pages')
  int totalPages;
  @JsonKey(name: 'total_results')
  int totalResults;

  MoviesResponse({
    @required this.results,
    this.dates,
    @required this.page,
    @required this.totalPages,
    this.totalResults,
  });

  /// Creates a [MoviesResponse] from a JSON object.
  factory MoviesResponse.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$MoviesResponseFromJson(json);
}
