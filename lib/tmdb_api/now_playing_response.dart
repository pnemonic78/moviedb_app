import 'dates.dart';
import 'movie.dart';

class MoviesNowPlayingResponse {
  final List<Movie> results;
  final Dates dates;
  final int page;
  final int totalPages;
  final int totalResult;

  MoviesNowPlayingResponse(
      this.results, this.dates, this.page, this.totalPages, this.totalResult);
}
