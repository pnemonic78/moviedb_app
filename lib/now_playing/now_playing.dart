import 'package:flutter/material.dart';
import 'package:tmdb/movies/movies_page.dart';
import 'package:tmdb/now_playing/now_playing_grid.dart';
import 'package:tmdb/now_playing/now_playing_list.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

class NowPlayingPage extends MoviesPage {
  NowPlayingPage({Key key}) : super(key: key);

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
  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api) async {
    return api.getNowPlaying(context);
  }

  @override
  Widget buildList(
      List<Movie> movies, bool showAsList, ValueChanged<Movie> onMovieTap) {
    return showAsList
        ? NowPlayingListPage(movies: movies, onMovieTap: onMovieTap)
        : NowPlayingGridPage(movies: movies, onMovieTap: onMovieTap);
  }
}
