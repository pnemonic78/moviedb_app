import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

import 'movie_bloc.dart';
import 'movies_grid_page.dart';
import 'movies_list_page.dart';

abstract class MoviesPage extends StatefulWidget {
  MoviesPage() : super();
}

abstract class MoviesState<P extends MoviesPage> extends State<P> {
  Widget buildList(
    List<Movie> movies,
    bool showAsList,
    ValueChanged<Movie> onMovieTap,
  ) {
    return showAsList
        ? MoviesListPage(movies: movies, onMovieTap: onMovieTap)
        : MoviesGridPage(movies: movies, onMovieTap: onMovieTap);
  }

  /// Function to call when a [Movie] is tapped.
  void _onTapMovie(Movie movie) {
    setState(() {
      _navigateToDetails(movie);
    });
  }

  /// Navigates to the movie details.
  void _navigateToDetails(Movie movie) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MovieDetailsHomePage(
                  movie: movie,
                )));
  }

  Widget _buildPage(BuildContext context, MovieBloc movieBloc) {
    final theme = Theme.of(context);
    final string = AppLocalizations.of(context);
    final title = getTitle(context);

    final header = Text(
      title,
      style: theme.textTheme.headline6,
    );

    final state = movieBloc.state;
    final movies = getMovies(state);

    Widget content;

    if (movies != null) {
      content = buildList(
        movies.results,
        state.showAsList,
        _onTapMovie,
      );
    } else if (state.error == null) {
      _fetchMovies(context, movieBloc);

      content = Center(child: CircularProgressIndicator());
    } else {
      content = Center(
        child: Icon(
          Icons.error_outline,
          size: errorIconSize,
        ),
      );
    }

    final body = Padding(
      padding: paddingAll_8,
      child: Container(
        child: Column(
          children: <Widget>[
            header,
            Expanded(
              child: content,
            ),
          ],
        ),
      ),
    );

    final iconViewStyle = IconButton(
      icon:
          state.showAsList ? Icon(MdiIcons.viewGrid) : Icon(MdiIcons.viewList),
      onPressed: () => movieBloc.add(new ToggleViewStyleEvent()),
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(string.title),
        actions: [
          iconViewStyle,
        ],
      ),
      body: body,
    );
  }

  @override
  Widget build(BuildContext context) {
    // ignore: close_sinks
    final movieBloc = context.bloc<MovieBloc>();

    return BlocProvider(
      create: (_) => movieBloc,
      child: BlocBuilder<MovieBloc, MovieState>(
        builder: (context, state) {
          // ignore: close_sinks
          final movieBloc = context.bloc<MovieBloc>();
          return _buildPage(context, movieBloc);
        },
      ),
    );
  }

  String getTitle(BuildContext context) {
    return "";
  }

  void _fetchMovies(BuildContext context, MovieBloc movieBloc) {
    final api = InjectorWidget.of(context).api;

    fetchMovies(context, api)
        .timeout(fetchTimeout)
        .then((response) => movieBloc.add(createResponseEvent(response)))
        .catchError((e) => movieBloc.add(MovieError(e)));
  }

  MoviesResponse getMovies(MovieState state);

  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api);

  MoviesResponseEvent createResponseEvent(MoviesResponse response);
}
