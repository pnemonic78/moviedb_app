import 'package:flutter/widgets.dart';
import 'package:tmdb/movies/movie_grid_tile.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

class MoviesSlider extends StatelessWidget {
  final List<Movie> movies;
  final ValueChanged<Movie> onTap;

  MoviesSlider({Key key, @required this.movies, this.onTap})
      : assert(movies != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final screenSize = media.size;
    final cellRatio = 0.49 + (-0.02 * screenSize.aspectRatio);
    final cellWidth = posterGridWidth;
    final cellHeight = cellWidth / cellRatio;

    return Container(
      height: cellHeight,
      child: ListView(
        scrollDirection: Axis.horizontal,
        children: _buildList(context, movies),
      ),
    );
  }

  List<Widget> _buildList(BuildContext context, List<Movie> movies) {
    final list = <Widget>[];

    for (var movie in movies) {
      list.add(MovieGridTile(
        movie: movie,
        onTap: onTap,
      ));
    }

    return list;
  }
}
