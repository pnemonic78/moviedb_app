import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';

import 'movie.dart';

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

  @override
  void didUpdateWidget(MovieDetailsHomePage oldWidget) {
    super.didUpdateWidget(oldWidget);
    if ((_movie == null) || (oldWidget.movie.id != widget.movie.id)) {
      _fetchMovie();
    }
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    if (_movie == null) {
      _fetchMovie();
    }
  }

  Future<void> _fetchMovie() async {
    _fetchMovieDetails(widget.movie).then((value) {
      _movie = value;
      setState(() {
        _updateUI();
      });
    });
  }

  Future<MovieDetails> _fetchMovieDetails(Movie movie) async {
    if (_api == null) _api = TMDBApi();
    return await _api.getMovieDetails(movie);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Padding(
        padding: paddingAll_8,
        child: (_movie != null)
            ? MovieDetailsWidget(movie: _movie)
            : Center(child: CircularProgressIndicator()),
      ),
    );
  }

  void _updateUI() {}
}
