import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

import 'movie_bloc.dart';
import 'movies_slider.dart';

class MoviesAllPage extends StatefulWidget {
  MoviesAllPage() : super();

  @override
  State<StatefulWidget> createState() => _MoviesAllPageState();
}

class _MoviesAllPageState extends State<MoviesAllPage> {
  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (_) => MovieBloc(),
      child: BlocBuilder<MovieBloc, MovieState>(
        builder: (context, state) {
          return _buildPage(context);
        },
      ),
    );
  }

  Widget _buildPage(BuildContext context) {
    final theme = Theme.of(context);
    final string = AppLocalizations.of(context);

    final popularTitle = Text(
      string.popular,
      style: theme.textTheme.headline5,
    );

    final popularList = _buildPopularList(context);

    final nowPlayingTitle = Text(
      string.now_playing,
      style: theme.textTheme.headline5,
    );

    final nowPlayingList = _buildNowPlayingList(context);

    final upcomingTitle = Text(
      string.upcoming,
      style: theme.textTheme.headline5,
    );

    final upcomingList = _buildUpcomingList(context);

    final topRatedTitle = Text(
      string.top_rated,
      style: theme.textTheme.headline5,
    );

    final topRatedList = _buildTopRatedList(context);

    final body = SingleChildScrollView(
      child: Padding(
        padding: paddingAll_8,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            popularTitle,
            popularList,
            nowPlayingTitle,
            nowPlayingList,
            upcomingTitle,
            upcomingList,
            topRatedTitle,
            topRatedList,
          ],
        ),
      ),
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(string.title),
      ),
      body: body,
    );
  }

  Widget _buildNowPlayingList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesNowPlaying(context),
      onTap: _onMovieTap,
    );
  }

  Widget _buildPopularList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesPopular(context),
      onTap: _onMovieTap,
    );
  }

  Widget _buildTopRatedList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesTopRated(context),
      onTap: _onMovieTap,
    );
  }

  Widget _buildUpcomingList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesUpcoming(context),
      onTap: _onMovieTap,
    );
  }

  List<Movie> getMoviesNowPlaying(BuildContext context) {
    // ignore: close_sinks
    final moviesBloc = context.bloc<MovieBloc>();
    final blocMovies = moviesBloc.state.moviesNowPlaying;
    if (blocMovies == null) {
      final api = InjectorWidget.of(context).api;

      api
          .getNowPlaying(context)
          .timeout(fetchTimeout)
          .then((response) => moviesBloc.add(NowPlayingResponseEvent(response)))
          .catchError((e) => moviesBloc.addError(e));

      return List<Movie>();
    }
    return blocMovies.results;
  }

  List<Movie> getMoviesPopular(BuildContext context) {
    // ignore: close_sinks
    final moviesBloc = context.bloc<MovieBloc>();
    final blocMovies = moviesBloc.state.moviesPopular;
    if (blocMovies == null) {
      final api = InjectorWidget.of(context).api;

      api
          .getPopular(context)
          .timeout(fetchTimeout)
          .then((response) => moviesBloc.add(PopularResponseEvent(response)))
          .catchError((e) => moviesBloc.addError(e));

      return List<Movie>();
    }
    return blocMovies.results;
  }

  List<Movie> getMoviesTopRated(BuildContext context) {
    // ignore: close_sinks
    final moviesBloc = context.bloc<MovieBloc>();
    final blocMovies = moviesBloc.state.moviesTopRated;
    if (blocMovies == null) {
      final api = InjectorWidget.of(context).api;

      api
          .getTopRated(context)
          .timeout(fetchTimeout)
          .then((response) => moviesBloc.add(TopRatedResponseEvent(response)))
          .catchError((e) => moviesBloc.addError(e));

      return List<Movie>();
    }
    return blocMovies.results;
  }

  List<Movie> getMoviesUpcoming(BuildContext context) {
    // ignore: close_sinks
    final moviesBloc = context.bloc<MovieBloc>();
    final blocMovies = moviesBloc.state.moviesUpcoming;
    if (blocMovies == null) {
      final api = InjectorWidget.of(context).api;

      api
          .getUpcoming(context)
          .timeout(fetchTimeout)
          .then((response) => moviesBloc.add(UpcomingResponseEvent(response)))
          .catchError((e) => moviesBloc.addError(e));

      return List<Movie>();
    }
    return blocMovies.results;
  }

  void _onMovieTap(Movie movie) {
    _navigateToMovie(movie);
  }

  /// Navigates to the movie details.
  void _navigateToMovie(Movie movie) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MovieDetailsHomePage(
                  movie: movie,
                )));
  }
}
