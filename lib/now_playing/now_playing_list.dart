import 'package:flutter/material.dart';
import 'package:tmdb/movies/movies_list_page.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

class NowPlayingListPage extends MoviesListPage {
  NowPlayingListPage({
    Key key,
    List<Movie> movies,
    ValueChanged<Movie> onMovieTap,
  }) : super(key: key, movies: movies, onMovieTap: onMovieTap);

  @override
  _NowPlayingListPageState createState() => _NowPlayingListPageState();
}

class _NowPlayingListPageState extends MoviesListState<NowPlayingListPage> {}
