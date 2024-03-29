import 'package:flutter/material.dart';
import 'package:tmdb/movies/bloc/movie_bloc.dart';
import 'package:tmdb/movies/movies_page.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

class NowPlayingPage extends MoviesPage {
  const NowPlayingPage({super.key});

  @override
  MoviesState<NowPlayingPage> createState() => _NowPlayingPageState();
}

class _NowPlayingPageState extends MoviesState<NowPlayingPage> {
  @override
  String getTitle(BuildContext context) {
    final string = AppLocalizations.get(context);
    return string.now_playing;
  }

  @override
  MoviesResponse? getMovies(MovieState state) => state.moviesNowPlaying;

  @override
  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api) async {
    return api.getNowPlaying(context);
  }

  @override
  MoviesResponseEvent createResponseEvent(MoviesResponse response) =>
      NowPlayingResponseEvent(response);
}
