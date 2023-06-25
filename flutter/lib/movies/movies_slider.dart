import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

import 'movies_all_tile.dart';

class MoviesSlider extends StatelessWidget {
  final List<Movie> movies;
  final ValueChanged<Movie> onTap;

  const MoviesSlider({super.key, required this.movies, required this.onTap});

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    final titleHeight = textTheme.titleLarge?.fontSize ?? 20;
    final tileHeight = paddingVertical_8.top +
        posterGridHeight +
        titleHeight +
        paddingVertical_8.bottom;

    return SizedBox(
      height: tileHeight,
      width: double.infinity,
      child: movies.isEmpty
          ? const Center(child: CircularProgressIndicator())
          : ListView(
              scrollDirection: Axis.horizontal,
              children: _buildList(context, movies),
            ),
    );
  }

  List<Widget> _buildList(BuildContext context, List<Movie> movies) {
    final list = <Widget>[];

    for (var movie in movies) {
      list.add(MoviesAllTile(
        movie: movie,
        onTap: onTap,
      ));
    }

    return list;
  }
}
