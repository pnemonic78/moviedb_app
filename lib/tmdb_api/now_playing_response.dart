import 'package:flutter/foundation.dart';

import 'model/dates.dart';
import 'model/movie.dart';

class MoviesNowPlayingResponse {
  List<Movie> results;
  Dates dates;
  int page;
  int totalPages;
  int totalResults;

  MoviesNowPlayingResponse({
    @required this.results,
    this.dates,
    @required this.page,
    @required this.totalPages,
    this.totalResults,
  });

  /// Creates a [MoviesNowPlayingResponse] from a JSON object.
  factory MoviesNowPlayingResponse.fromJson(Map<String, dynamic> json) {
    var list = json['results'] as List;
    List<Movie> results = list.map((i) => Movie.fromJson(i)).toList();

    return MoviesNowPlayingResponse(
      results: results,
      dates: Dates.fromJson(json['dates']),
      page: json['page'],
      totalPages: json['total_pages'],
      totalResults: json['total_results'],
    );
  }
}
