import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';
import 'package:tmdb/tmdb_api/video.dart';

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
  TMDBApi _api = TMDBApi();
  MovieDetails _movie;

  Future<MovieDetails> _fetchMovie() async {
    return _fetchMovieDetails(widget.movie);
  }

  Future<MovieDetails> _fetchMovieDetails(Movie movie) async {
    _movie = await _api.getMovieDetails(context, movie);
    return _movie;
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<MovieDetails>(
      future: _fetchMovie(),
      builder: (BuildContext context, AsyncSnapshot<MovieDetails> snapshot) {
        Widget content;
        if ((snapshot.connectionState == ConnectionState.done) &&
            snapshot.hasData) {
          content = MovieDetailsWidget(
            movie: snapshot.data,
            onVideoTap: _onVideoTap,
          );
        } else {
          content = Center(child: CircularProgressIndicator());
        }

        return Scaffold(
          appBar: AppBar(
            title: Text(widget.title),
          ),
          body: Padding(
            padding: paddingAll_8,
            child: content,
          ),
        );
      },
    );
  }

  /// Function to call when a [Video] is tapped.
  void _onVideoTap(Video video) {
    //setState(() {
    _playVideo(video);
    //});
  }

  /// Plays the video.
  void _playVideo(Video video) {
    //TODO implement me!
    print('play video: $video');
  }
}
