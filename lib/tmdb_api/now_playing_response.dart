import 'package:flutter/foundation.dart';

import 'dates.dart';
import 'movie.dart';

class MoviesNowPlayingResponse {
  final List<Movie> results;
  final Dates dates;
  final int page;
  final int totalPages;
  final int totalResults;

  const MoviesNowPlayingResponse(
      {@required this.results,
      this.dates,
      @required this.page,
      @required this.totalPages,
      this.totalResults});

  /// Creates a [MoviesNowPlayingResponse] from a JSON object.
  factory MoviesNowPlayingResponse.fromJson(Map<String, dynamic> json) {
    final list = json['results'] as List;
    List<Movie> results = list.map((j) => Movie.fromJson(j)).toList();

    return MoviesNowPlayingResponse(
      results: results,
      dates: Dates.fromJson(json['dates']),
      page: json['page'],
      totalPages: json['total_pages'],
      totalResults: json['total_results'],
    );
  }
}
