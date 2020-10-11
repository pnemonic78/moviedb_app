import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/main/main_bloc.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

abstract class MoviesPage extends StatefulWidget {
  MoviesPage() : super();
}

abstract class MoviesState<P extends MoviesPage> extends State<P> {
  MoviesResponse _movies;

  Widget buildList(
    List<Movie> movies,
    bool showAsList,
    ValueChanged<Movie> onMovieTap,
  );

  /// Function to call when a [Movie] is tapped.
  void _onMovieTap(Movie movie) {
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

  Widget _buildPage(BuildContext context, MainState state) {
    final theme = Theme.of(context);
    final string = AppLocalizations.of(context);
    final title = getTitle(context);

    final header = Text(
      title,
      style: theme.textTheme.headline6,
    );

    return FutureBuilder<MoviesResponse>(
      future: _fetchMovies(context),
      builder: (BuildContext context, AsyncSnapshot<MoviesResponse> snapshot) {
        // ignore: close_sinks
        final mainBloc = context.bloc<MainBloc>();

        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            content = buildList(
              snapshot.data.results,
              state.showAsList,
              _onMovieTap,
            );
          } else {
            content = Center(
              child: Icon(
                Icons.error_outline,
                size: errorIconSize,
              ),
            );
          }
        } else {
          content = Center(child: CircularProgressIndicator());
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
          icon: state.showAsList
              ? Icon(MdiIcons.viewGrid)
              : Icon(MdiIcons.viewList),
          onPressed: () => mainBloc.add(new ToggleViewStyleEvent()),
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
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (_) => MainBloc(),
      child: BlocBuilder<MainBloc, MainState>(
        builder: (context, state) {
          return _buildPage(context, state);
        },
      ),
    );
  }

  String getTitle(BuildContext context) {
    return "";
  }

  Future<MoviesResponse> _fetchMovies(BuildContext context) async {
    if (_movies == null) {
      final TMDBApi api = InjectorWidget.of(context).api;
      _movies = await fetchMovies(context, api);
    }
    return _movies;
  }

  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api);

// void _onIconViewStylePressed() {
// setState(() {
//   _showAsList = !_showAsList;
// });
// }
}
