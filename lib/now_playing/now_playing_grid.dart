import 'package:flutter/material.dart';
import 'package:tmdb/movies/movies_grid_page.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

class NowPlayingGridPage extends MoviesGridPage {
  NowPlayingGridPage({
    Key key,
    ValueChanged<MoviesGridPage> onListIconTap,
  }) : super(key: key, onListIconTap: onListIconTap);

  @override
  _NowPlayingGridPageState createState() => _NowPlayingGridPageState();
}

class _NowPlayingGridPageState extends MoviesGridState<NowPlayingGridPage> {
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
