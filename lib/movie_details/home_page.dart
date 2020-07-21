import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/poster_page.dart';
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

        final Movie movie = widget.movie;

        final screenSize = MediaQuery.of(context).size;
        final screenWidth = screenSize.width;

        final backdropWidth = screenWidth;
        final backdropHeight = backdropWidth * 9 / 16;
        final backdropUrl = TMDBApi.generatePosterUrl(
            movie.backdropPath, backdropWidth, backdropHeight);
        final backdrop = CachedNetworkImage(
          imageUrl: backdropUrl,
          placeholder: (context, url) => Icon(
            Icons.image,
            size: min(backdropWidth, backdropHeight),
          ),
          width: backdropWidth,
          height: backdropHeight,
        );

        final textTheme = Theme.of(context).textTheme;
        // Beware fo white text on light backdrop!
        final titleWidget = Text(
          movie.title,
          maxLines: 2,
          style: textTheme.headline5,
          overflow: TextOverflow.ellipsis,
        );

        final bodySlivers = CustomScrollView(
          slivers: <Widget>[
            SliverAppBar(
              expandedHeight: backdropHeight,
              pinned: true,
              flexibleSpace: FlexibleSpaceBar(
                title: titleWidget,
                background: backdrop,
              ),
            ),
            SliverFillRemaining(
              child: content,
              hasScrollBody: false,
            ),
          ],
        );

        return Scaffold(
          body: bodySlivers,
        );
      },
    );
  }

  /// Function to call when a poster [Image] is tapped.
  void _onPosterTap(MovieDetails movie) {
    setState(() {
      _navigateToPoster(movie);
    });
  }

  /// Navigates to the movie poster.
  void _navigateToPoster(MovieDetails movie) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MoviePosterPage(
                  movie: movie,
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
