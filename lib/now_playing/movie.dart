import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';

final _posterWidth = 99.75;
final _posterHeight = 150.0;
final _rowHeight = _posterHeight + 10;

class MovieWidget extends StatelessWidget {
  final Movie movie;

  const MovieWidget({Key key, @required this.movie})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final imageWidth = _posterWidth;
    final imageHeight = _posterHeight;
    final path =
        TMDBApi.generatePosterUrl(movie.posterPath, imageWidth, imageHeight);
    final poster = Image.network(
      path,
      width: imageWidth,
      height: imageHeight,
    );

    return Row(
      children: <Widget>[
        poster,
        Text(movie.title),
      ],
    );
  }
}
