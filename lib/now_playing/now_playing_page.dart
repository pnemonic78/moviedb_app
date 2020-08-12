import 'package:flutter/material.dart';
import 'package:tmdb/movies/movies_list_page.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

class NowPlayingHomePage extends MoviesListPage {
  NowPlayingHomePage({Key key}) : super(key: key);

  @override
  _NowPlayingHomePageState createState() => _NowPlayingHomePageState();
}

class _NowPlayingHomePageState extends MoviesListState<NowPlayingHomePage> {
  @override
  String getTitle(BuildContext context) {
    final string = AppLocalizations.of(context);
    return string.now_playing;
  }

  @override
  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api) async {
    return api.getNowPlaying(context);
  }
}
