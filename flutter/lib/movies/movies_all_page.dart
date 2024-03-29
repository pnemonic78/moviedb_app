import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/movies/now_playing_page.dart';
import 'package:tmdb/movies/popular_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

import 'bloc/movie_bloc.dart';
import 'movies_page.dart';
import 'movies_slider.dart';
import 'top_rated_page.dart';
import 'upcoming_page.dart';

class MoviesAllPage extends StatefulWidget {
  const MoviesAllPage({super.key});

  @override
  State<StatefulWidget> createState() => _MoviesAllPageState();
}

class _MoviesAllPageState extends State<MoviesAllPage> {
  @override
  Widget build(BuildContext context) {
    // ignore: close_sinks
    final movieBloc = context.read<MovieBloc>();

    return BlocProvider(
      create: (_) => movieBloc,
      child: BlocBuilder<MovieBloc, MovieState>(
        builder: (context, state) {
          return _buildPage(context);
        },
      ),
    );
  }

  Widget _buildPage(BuildContext context) {
    final string = AppLocalizations.get(context);

    final popularTitle = _buildTitle(
      context,
      string.popular,
      _onTapPopular,
    );

    final popularList = _buildPopularList(context);

    final nowPlayingTitle = _buildTitle(
      context,
      string.now_playing,
      _onTapNowPlaying,
    );

    final nowPlayingList = _buildNowPlayingList(context);

    final upcomingTitle = _buildTitle(
      context,
      string.upcoming,
      _onTapUpcoming,
    );

    final upcomingList = _buildUpcomingList(context);

    final topRatedTitle = _buildTitle(
      context,
      string.top_rated,
      _onTapTopRated,
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

  Widget _buildTitle(
    BuildContext context,
    String title,
    ValueChanged<String> onTap,
  ) {
    final theme = Theme.of(context);
    final text = Text(
      title,
      style: theme.textTheme.headlineSmall,
    );

    return InkWell(
      onTap: () => onTap(title),
      child: SizedBox(
        width: double.infinity,
        child: Padding(
          padding: paddingAll_8,
          child: text,
        ),
      ),
    );
  }

  Widget _buildNowPlayingList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesNowPlaying(context),
      onTap: _onTapMovie,
    );
  }

  Widget _buildPopularList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesPopular(context),
      onTap: _onTapMovie,
    );
  }

  Widget _buildTopRatedList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesTopRated(context),
      onTap: _onTapMovie,
    );
  }

  Widget _buildUpcomingList(BuildContext context) {
    return MoviesSlider(
      movies: getMoviesUpcoming(context),
      onTap: _onTapMovie,
    );
  }

  List<Movie> getMoviesNowPlaying(BuildContext context) {
    // ignore: close_sinks
    final movieBloc = context.read<MovieBloc>();
    final blocMovies = movieBloc.state.moviesNowPlaying;
    if (blocMovies == null) {
      if (movieBloc.state.error != null) {
        return [];
      }

      final api = InjectorWidget.get(context).api;

      api
          .getNowPlaying(context)
          .timeout(fetchTimeout)
          .then((response) => movieBloc.add(NowPlayingResponseEvent(response)))
          .catchError((e) => movieBloc.add(MovieError(e)));

      return [];
    }
    return blocMovies.results;
  }

  List<Movie> getMoviesPopular(BuildContext context) {
    // ignore: close_sinks
    final movieBloc = context.read<MovieBloc>();
    final blocMovies = movieBloc.state.moviesPopular;
    if (blocMovies == null) {
      if (movieBloc.state.error != null) {
        return [];
      }

      final api = InjectorWidget.get(context).api;
      api
          .getPopular(context)
          .timeout(fetchTimeout)
          .then((response) => movieBloc.add(PopularResponseEvent(response)))
          .catchError((e) => movieBloc.add(MovieError(e)));

      return [];
    }

    return blocMovies.results;
  }

  List<Movie> getMoviesTopRated(BuildContext context) {
    // ignore: close_sinks
    final movieBloc = context.read<MovieBloc>();
    final blocMovies = movieBloc.state.moviesTopRated;
    if (blocMovies == null) {
      if (movieBloc.state.error != null) {
        return [];
      }

      final api = InjectorWidget.get(context).api;
      api
          .getTopRated(context)
          .timeout(fetchTimeout)
          .then((response) => movieBloc.add(TopRatedResponseEvent(response)))
          .catchError((e) => movieBloc.add(MovieError(e)));

      return [];
    }
    return blocMovies.results;
  }

  List<Movie> getMoviesUpcoming(BuildContext context) {
    // ignore: close_sinks
    final movieBloc = context.read<MovieBloc>();
    final blocMovies = movieBloc.state.moviesUpcoming;
    if (blocMovies == null) {
      if (movieBloc.state.error != null) {
        return [];
      }

      final api = InjectorWidget.get(context).api;
      api
          .getUpcoming(context)
          .timeout(fetchTimeout)
          .then((response) => movieBloc.add(UpcomingResponseEvent(response)))
          .catchError((e) => movieBloc.add(MovieError(e)));

      return [];
    }
    return blocMovies.results;
  }

  /// Navigates to the movies page.
  void _navigateToPage(MoviesPage page) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => page));
  }

  void _onTapNowPlaying(String title) {
    _navigateToPage(const NowPlayingPage());
  }

  void _onTapPopular(String title) {
    _navigateToPage(const PopularPage());
  }

  void _onTapTopRated(String title) {
    _navigateToPage(const TopRatedPage());
  }

  void _onTapUpcoming(String title) {
    _navigateToPage(const UpcomingPage());
  }

  void _onTapMovie(Movie movie) {
    _navigateToMovie(movie);
  }

  /// Navigates to the movie details.
  void _navigateToMovie(Movie movie) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => MovieDetailsHomePage(movie: movie),
      ),
    );
  }
}
