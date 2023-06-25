import 'package:flutter/material.dart';
import 'package:tmdb/movies/bloc/movie_bloc.dart';
import 'package:tmdb/movies/movies_page.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

class PopularPage extends MoviesPage {
  PopularPage() : super();

  @override
  MoviesState<PopularPage> createState() => _PopularPageState();
}

class _PopularPageState extends MoviesState<PopularPage> {
  @override
  String getTitle(BuildContext context) {
    final string = AppLocalizations.get(context);
    return string.popular;
  }

  @override
  MoviesResponse? getMovies(MovieState state) => state.moviesPopular;

  @override
  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api) async {
    return api.getPopular(context);
  }

  @override
  MoviesResponseEvent createResponseEvent(MoviesResponse response) =>
      PopularResponseEvent(response);
}
