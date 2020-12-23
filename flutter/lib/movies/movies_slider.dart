import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

import 'movies_all_tile.dart';

class MoviesSlider extends StatelessWidget {
  final List<Movie> movies;
  final ValueChanged<Movie> onTap;

  MoviesSlider({Key key, @required this.movies, this.onTap}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    final titleHeight = textTheme.headline6.fontSize;
    final tileHeight = paddingVertical_8.top +
        posterGridHeight +
        titleHeight +
        paddingVertical_8.bottom;

    return Container(
      height: tileHeight,
      width: double.infinity,
      child: (movies == null)
          ? Center(
              child: Icon(
              Icons.error_outline,
              size: errorIconSize,
            ))
          : (movies.isEmpty
              ? Center(child: CircularProgressIndicator())
              : ListView(
                  scrollDirection: Axis.horizontal,
                  children: _buildList(context, movies),
                )),
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
