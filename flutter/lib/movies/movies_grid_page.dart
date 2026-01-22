import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

import 'movie_grid_tile.dart';

class MoviesGridPage extends StatefulWidget {
  final List<Movie> movies;
  final ValueChanged<Movie> onMovieTap;

  const MoviesGridPage({
    super.key,
    required this.movies,
    required this.onMovieTap,
  });

  @override
  State<MoviesGridPage> createState() => _MoviesGridState();
}

class _MoviesGridState<P extends MoviesGridPage> extends State<P> {
  //TODO can add listener to controller to load next page
  ScrollController? _scrollController;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController();
  }

  Widget _buildGrid(
    BuildContext context,
    List<Movie> movies,
    ValueChanged<Movie> onMovieTap,
  ) {
    final media = MediaQuery.of(context);
    final screenSize = media.size;
    final screenWidth = screenSize.width;
    const cellWidth = padding_8 + posterGridWidth + padding_8;
    final columnCount = screenWidth ~/ cellWidth;
    final columnWidth = screenWidth / columnCount;
    final cellRatio = 0.45;

    return GridView.builder(
      controller: _scrollController,
      itemBuilder: (BuildContext context, int index) => MovieGridTile(
        movie: movies[index],
        onTap: onMovieTap,
        width: columnWidth,
      ),
      itemCount: movies.length,
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: columnCount,
        childAspectRatio: cellRatio,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return _buildGrid(context, widget.movies, widget.onMovieTap);
  }
}
