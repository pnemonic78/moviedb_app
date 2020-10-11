import 'package:flutter/material.dart';
import 'package:inject/inject.dart';
import 'package:tmdb/movies/movie_bloc.dart';
import 'package:tmdb/movies/movies_page.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

@provide
class NowPlayingPage extends MoviesPage {
  NowPlayingPage() : super();

  @override
  _NowPlayingPageState createState() => _NowPlayingPageState();
}

class _NowPlayingPageState extends MoviesState<NowPlayingPage> {
  @override
  String getTitle(BuildContext context) {
    final string = AppLocalizations.of(context);
    return string.now_playing;
  }

  @override
  MoviesResponse getMovies(MovieState state) {
    return state.moviesNowPlaying;
  }

  @override
  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api) async {
    return api.getNowPlaying(context);
  }
}
