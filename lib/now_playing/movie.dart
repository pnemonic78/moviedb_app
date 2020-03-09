import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/movie.dart';

final _rowHeight = 40.0;

class MovieWidget extends StatelessWidget {
  final Movie movie;

  const MovieWidget({Key key, @required this.movie})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: _rowHeight,
      child: Text(movie.title),
    );
  }
}
