import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';

import 'movie_screen.dart';

class MovieDetailsHomePage extends StatefulWidget {
  MovieDetailsHomePage({Key key, this.title, this.movie})
      : assert(movie != null),
        super(key: key);

  final String title;
  final Movie movie;

  @override
  _MovieDetailsHomePageState createState() => _MovieDetailsHomePageState();
}

class _MovieDetailsHomePageState extends State<MovieDetailsHomePage> {
  TMDBApi _api;
  MovieDetails _movie;

  Future<MovieDetails> _fetchMovie() async {
    return _fetchMovieDetails(widget.movie);
  }

  Future<MovieDetails> _fetchMovieDetails(Movie movie) async {
    if (_api == null) _api = TMDBApi();
    _movie = await _api.getMovieDetails(context, movie);
    return _movie;
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<MovieDetails>(
      future: _fetchMovie(),
      builder: (BuildContext context, AsyncSnapshot<MovieDetails> snapshot) {
        Widget child;
        if (snapshot.connectionState == ConnectionState.done) {
          child = MovieDetailsWidget(movie: snapshot.data);
        } else {
          child = Center(child: CircularProgressIndicator());
        }

        return Scaffold(
          appBar: AppBar(
            title: Text(widget.title),
          ),
          body: Padding(
            padding: paddingAll_8,
            child: child,
          ),
        );
      },
    );
  }
}
