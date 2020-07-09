import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/strings.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';
import 'package:tmdb/tmdb_api/now_playing_response.dart';

import 'movie_tile.dart';

class NowPlayingHomePage extends StatefulWidget {
  final String title;

  NowPlayingHomePage({Key key, this.title}) : super(key: key);

  @override
  _NowPlayingHomePageState createState() => _NowPlayingHomePageState();
}

class _NowPlayingHomePageState extends State<NowPlayingHomePage> {
  final _api = TMDBApi();

  Widget _buildListWidgets(List<Movie> movies) {
    return ListView.builder(
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
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MovieDetailsHomePage(
                  title: widget.title,
                  movie: movie,
                )));
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    final header = Text(
      R.string.now_playing,
      style: theme.textTheme.headline6,
    );

    return FutureBuilder<MoviesNowPlayingResponse>(
      future: _api.getNowPlaying(context),
      builder: (BuildContext context,
          AsyncSnapshot<MoviesNowPlayingResponse> snapshot) {
        Widget content;
        if ((snapshot.connectionState == ConnectionState.done) &&
            snapshot.hasData) {
          content = _buildListWidgets(snapshot.data.results);
        } else {
          content = Center(child: CircularProgressIndicator());
        }

        return Scaffold(
          appBar: AppBar(
            title: Text(widget.title),
          ),
          body: Padding(
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
          ),
        );
      },
    );
  }
}
