import 'package:flutter/material.dart';
import 'package:tmdb/movies/movie_bloc.dart';
import 'package:tmdb/movies/movies_page.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

class UpcomingPage extends MoviesPage {
  UpcomingPage() : super();

  @override
  _UpcomingPageState createState() => _UpcomingPageState();
}

class _UpcomingPageState extends MoviesState<UpcomingPage> {
  @override
  String getTitle(BuildContext context) {
    final string = AppLocalizations.of(context);
    return string.upcoming;
  }

  @override
  MoviesResponse getMovies(MovieState state) => state.moviesUpcoming;

  @override
  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api) async {
    return api.getUpcoming(context);
  }

  @override
  MoviesResponseEvent createResponseEvent(MoviesResponse response) =>
      UpcomingResponseEvent(response);
}
