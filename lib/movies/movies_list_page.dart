import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

import 'movie_list_tile.dart';

abstract class MoviesListPage extends StatefulWidget {
  final String title;

  MoviesListPage({Key key, this.title}) : super(key: key);
}

abstract class MoviesListState<P extends MoviesListPage> extends State<P> {
  final TMDBApi _api = TMDBApi();

  //TODO can add listener to controller to load next page
  ScrollController _scrollController;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController();
  }

  Widget _buildListWidgets(List<Movie> movies) {
    return ListView.builder(
      controller: _scrollController,
      itemBuilder: (BuildContext context, int index) => MovieListTile(
        movie: movies[index],
        onTap: _onMovieTap,
      ),
      itemCount: movies.length,
    );
  }

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
            content = _buildListWidgets(snapshot.data.results);
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

        return Scaffold(
          appBar: AppBar(
            title: Text(string.title),
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
    return fetchMovies(context, _api);
  }

  Future<MoviesResponse> fetchMovies(BuildContext context, TMDBApi api);
}
