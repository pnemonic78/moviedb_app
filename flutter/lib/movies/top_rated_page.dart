import 'package:flutter/material.dart';
import 'package:tmdb/movies/bloc/movie_bloc.dart';
import 'package:tmdb/movies/movies_page.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

class TopRatedPage extends MoviesPage {
  TopRatedPage() : super();

  @override
  _TopRatedPageState createState() => _TopRatedPageState();
}

class _TopRatedPageState extends MoviesState<TopRatedPage> {
  @override
  String getTitle(BuildContext context) {
    final string = AppLocalizations.of(context);
    return string.top_rated;
  }

  @override
  MoviesResponse getMovies(MovieState state) => state.moviesTopRated;

  @override
  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api) async {
    return api.getTopRated(context);
  }

  @override
  MoviesResponseEvent createResponseEvent(MoviesResponse response) =>
      TopRatedResponseEvent(response);
}
