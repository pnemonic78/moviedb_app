import 'package:bordered_text/bordered_text.dart';
import 'package:flutter/material.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/movie_details/poster_page.dart';
import 'package:tmdb/person/person_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/media_cast.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:tmdb/videos/video_page.dart';

import 'movie_screen.dart';

class MovieDetailsHomePage extends StatefulWidget {
  Movie movie;

  MovieDetailsHomePage({super.key, required this.movie});

  @override
  State<MovieDetailsHomePage> createState() => _MovieDetailsHomePageState();
}

class _MovieDetailsHomePageState extends State<MovieDetailsHomePage> {
  MovieDetails? _movie;

  Stream<MovieDetails> _fetchMovie(BuildContext context) async* {
    if (_movie != null) {
      yield _movie!;
      return;
    }
    yield* _fetchMovieDetails(context, widget.movie);
  }

  Stream<MovieDetails> _fetchMovieDetails(
    BuildContext context,
    Movie movie,
  ) async* {
    MovieDetails details = MovieDetails.of(movie);
    _movie = details;
    yield details;

    final TMDBApi api = InjectorWidget.get(context).api;
    details = await api.getMovieDetails(context, movie);
    _movie = details;
    yield details;
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<MovieDetails>(
      stream: _fetchMovie(context),
      builder: (BuildContext context, AsyncSnapshot<MovieDetails> snapshot) {
        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            final MovieDetails movie = snapshot.data!;
            widget.movie = movie;
            content = MovieDetailsWidget(
              movie: movie,
              onTapPoster: _onTapPoster,
              onVideoTap: _onTapVideo,
              onCastTap: _onTapCast,
            );
          } else {
            content = const Center(
              child: Icon(
                Icons.error_outline,
                size: errorIconSize,
              ),
            );
          }
        } else {
          content = const Center(child: CircularProgressIndicator());
        }

        final movie = widget.movie;

        final media = MediaQuery.of(context);
        final screenSize = media.size;
        final screenWidth = screenSize.width;

        final backdropWidth = screenWidth;
        final backdropHeight = backdropWidth * 9 / 16;
        final backdrop = TMDBApi.generateBackdrop(
          backdropPath: movie.backdropPath,
          backdropWidth: backdropWidth,
          backdropHeight: backdropHeight,
        );

        final textTheme = Theme.of(context).textTheme;
        final titleStyle = textTheme.headlineSmall!;
        final luminance = titleStyle.color?.computeLuminance() ?? 0.5;
        final titleWidget = BorderedText(
          strokeWidth: 2.0,
          strokeColor: (luminance > 0.5 ? Colors.black54 : Colors.white54),
          child: Text(
            movie.getTitle() ?? "",
            maxLines: 3,
            style: titleStyle,
            overflow: TextOverflow.ellipsis,
          ),
        );

        final bodySlivers = CustomScrollView(
          slivers: <Widget>[
            SliverAppBar(
              expandedHeight: backdropHeight,
              pinned: true,
              flexibleSpace: FlexibleSpaceBar(
                title: titleWidget,
                centerTitle: true,
                background: backdrop,
              ),
            ),
            SliverFillRemaining(
              hasScrollBody: false,
              child: content,
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
  void _onTapPoster(MovieDetails movie) {
    setState(() {
      _navigateToPoster(movie);
    });
  }

  /// Navigates to the movie poster.
  void _navigateToPoster(MovieDetails movie) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => MoviePosterPage(movie: movie)),
    );
  }

  /// Function to call when a [MovieVideo] is tapped.
  void _onTapVideo(MovieVideo video) {
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
          title: _movie?.getTitle() ?? "",
          video: video,
        ),
      ),
    );
  }

  /// Function to call when a [MediaCast] is tapped.
  void _onTapCast(MediaCast cast) {
    setState(() {
      _navigateToPerson(cast);
    });
  }

  /// Navigates to the person.
  void _navigateToPerson(Person person) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => PersonPage(
          title: person.getTitle() ?? "",
          person: person,
        ),
      ),
    );
  }
}
