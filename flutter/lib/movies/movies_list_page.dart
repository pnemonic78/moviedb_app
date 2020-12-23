import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

import 'movie_list_tile.dart';

class MoviesListPage extends StatefulWidget {
  final String title;
  final List<Movie> movies;
  final ValueChanged<Movie> onMovieTap;

  MoviesListPage({Key key, this.title, this.movies, this.onMovieTap})
      : super(key: key);

  @override
  _MoviesListState createState() => _MoviesListState();
}

class _MoviesListState<P extends MoviesListPage> extends State<P> {
  //TODO can add listener to controller to load next page
  ScrollController _scrollController;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController();
  }

  Widget _buildList(List<Movie> movies, ValueChanged<Movie> onMovieTap) {
    return ListView.builder(
      controller: _scrollController,
      itemBuilder: (BuildContext context, int index) => MovieListTile(
        movie: movies[index],
        onTap: onMovieTap,
      ),
      itemCount: movies.length,
    );
  }

  @override
  Widget build(BuildContext context) {
    return _buildList(widget.movies, widget.onMovieTap);
  }
}
