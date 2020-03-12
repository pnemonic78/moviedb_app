import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';

import 'movie.dart';

class MovieDetailsHomePage extends StatefulWidget {
  MovieDetailsHomePage({Key key, this.title, this.movie})
      : assert(movie != null),
        super(key: key);

  final String title;
  final MovieDetails movie;

  @override
  _MovieDetailsHomePageState createState() => _MovieDetailsHomePageState();
}

class _MovieDetailsHomePageState extends State<MovieDetailsHomePage> {
  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    final header = Text(
      'Movie Details',
      style: theme.textTheme.headline6,
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: MovieDetailsWidget(movie: widget.movie),
      ),
    );
  }
}