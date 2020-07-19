import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/poster_page.dart';
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
  final _api = TMDBApi();
  MovieDetails _movie;

  Stream<MovieDetails> _fetchMovie() async* {
    if (_movie != null) {
      yield _movie;
      return;
    }
    yield* _fetchMovieDetails(widget.movie);
  }

  Stream<MovieDetails> _fetchMovieDetails(Movie movie) async* {
    _movie = MovieDetails.of(movie);
    yield _movie;
    _movie = await _api.getMovieDetails(context, movie);
    yield _movie;
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<MovieDetails>(
      stream: _fetchMovie(),
      builder: (BuildContext context, AsyncSnapshot<MovieDetails> snapshot) {
        Widget content;
        if ((snapshot.connectionState == ConnectionState.done) &&
            snapshot.hasData) {
          final Movie movie = snapshot.data;
          content = MovieDetailsWidget(
            movie: movie,
            onPosterTap: _onPosterTap,
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

  /// Function to call when a poster [Image] is tapped.
  void _onPosterTap(String posterPath) {
    setState(() {
      _navigateToPoster(posterPath);
    });
  }

  /// Navigates to the movie poster.
  void _navigateToPoster(String posterPath) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MoviePosterPage(
                  title: widget.title,
                  name: widget.movie.title,
                  path: posterPath,
                )));
  }

  /// Function to call when a [MovieVideo] is tapped.
  void _onVideoTap(MovieVideo video) {
    setState(() {
      _navigateToVideo(video);
    });
  }

  /// Navigates to the movie video.
  void _navigateToVideo(MovieVideo video) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => VideoPlayerPage(
                  title: widget.title,
                  video: video,
                )));
  }
}
