import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';
import 'package:tmdb/tmdb_api/video.dart';

import 'movie_screen.dart';
import 'video_page.dart';

class MovieDetailsHomePage extends StatefulWidget {
  final String title;
  final Movie movie;

  MovieDetailsHomePage({Key key, this.title, this.movie})
      : assert(movie != null),
        super(key: key);

  @override
  _MovieDetailsHomePageState createState() => _MovieDetailsHomePageState();
}

class _MovieDetailsHomePageState extends State<MovieDetailsHomePage> {
  TMDBApi _api = TMDBApi();
  MovieDetails _movie;

  Future<MovieDetails> _fetchMovie() async {
    return _movie ?? _fetchMovieDetails(widget.movie);
  }

  Future<MovieDetails> _fetchMovieDetails(Movie movie) async {
    _movie = await _api.getMovieDetails(context, movie, false);
    return _movie;
  }

  @override
  Widget build(BuildContext context) {
    final futureCached = FutureBuilder<MovieDetails>(
      future: _api.getMovieDetails(context, widget.movie, true),
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

        return content;
      },
    );

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
          content = futureCached;
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
    setState(() {
      _navigateToVideo(video);
    });
  }

  /// Navigates to the movie video.
  void _navigateToVideo(Video video) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => VideoPlayerPage(
                  title: widget.title,
                  video: video,
                )));
  }
}
