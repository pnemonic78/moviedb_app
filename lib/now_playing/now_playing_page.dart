import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

import 'movie_tile.dart';

class NowPlayingHomePage extends StatefulWidget {
  NowPlayingHomePage({Key key}) : super(key: key);

  @override
  _NowPlayingHomePageState createState() => _NowPlayingHomePageState();
}

class _NowPlayingHomePageState extends State<NowPlayingHomePage> {
  final _api = TMDBApi();

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
      itemBuilder: (BuildContext context, int index) => MovieTile(
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
    final string = AppLocalizations.of(context);

    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MovieDetailsHomePage(
                  title: string.now_playing,
                  movie: movie,
                )));
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final string = AppLocalizations.of(context);

    final header = Text(
      string.now_playing,
      style: theme.textTheme.headline6,
    );

    return FutureBuilder<MoviesResponse>(
      future: _api.getNowPlaying(context),
      builder: (BuildContext context,
          AsyncSnapshot<MoviesResponse> snapshot) {
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
}
