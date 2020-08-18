import 'package:flutter/material.dart';
import 'package:tmdb/movies/movies_grid_page.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

class NowPlayingGridPage extends MoviesGridPage {
  NowPlayingGridPage({
    Key key,
    List<Movie> movies,
    ValueChanged<Movie> onMovieTap,
  }) : super(key: key, movies: movies, onMovieTap: onMovieTap);

  @override
  _NowPlayingGridPageState createState() => _NowPlayingGridPageState();
}

class _NowPlayingGridPageState extends MoviesGridState<NowPlayingGridPage> {}
