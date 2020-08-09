import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';

import 'model/dates.dart';
import 'model/movie.dart';

part 'now_playing_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class MoviesNowPlayingResponse {
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

  MoviesNowPlayingResponse({
    @required this.results,
    this.dates,
    @required this.page,
    @required this.totalPages,
    this.totalResults,
  });

  /// Creates a [MoviesNowPlayingResponse] from a JSON object.
  factory MoviesNowPlayingResponse.fromJson(Map<String, dynamic> json) =>
      (json != null) ? _$MoviesNowPlayingResponseFromJson(json) : null;
}
