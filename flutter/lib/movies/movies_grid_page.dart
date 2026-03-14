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
  State<MoviesGridPage> createState() => MoviesGridState();
}

class MoviesGridState extends State<MoviesGridPage> {
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
    final pageWidth = screenWidth - paddingAll_8.left - paddingAll_8.right;

    var tile = MovieGridTile(
      movie: movies[0],
      onTap: onMovieTap,
    );
    final cellSize = tile.size(context);
    final cellWidth = cellSize.width + paddingAll_8.right;
    final columnCount = pageWidth ~/ cellWidth;
    final columnWidth = pageWidth / columnCount;

    tile = MovieGridTile(
      movie: movies[0],
      onTap: onMovieTap,
      width: columnWidth,
    );
    final tileSize = tile.size(context);
    final tileHeight = tileSize.height;
    final cellRatio = columnWidth / tileHeight;

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
