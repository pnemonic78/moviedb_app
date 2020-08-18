import 'package:flutter/material.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

abstract class MoviesPage extends StatefulWidget {
  final String title;
  final ValueChanged<MoviesPage> onViewIconTap;

  MoviesPage({Key key, this.title, this.onViewIconTap}) : super(key: key);
}

abstract class MoviesState<P extends MoviesPage> extends State<P> {
  final TMDBApi _api = TMDBApi();
  MoviesResponse _movies;
  bool _showAsList = false;

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

  @override
  Widget build(BuildContext context) {
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
        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            content = buildList(
              snapshot.data.results,
              _showAsList,
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
          icon: _showAsList ? Icon(MdiIcons.viewGrid) : Icon(MdiIcons.viewList),
          onPressed: () => _onIconViewStylePressed(),
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

  String getTitle(BuildContext context) {
    return "";
  }

  Future<MoviesResponse> _fetchMovies(BuildContext context) async {
    if (_movies == null) {
      _movies = await fetchMovies(context, _api);
    }
    return _movies;
  }

  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api);

  void _onIconViewStylePressed() {
    setState(() {
      _showAsList = !_showAsList;
    });
  }
}
