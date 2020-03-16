import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/strings.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';

import 'movie_tile.dart';

class NowPlayingHomePage extends StatefulWidget {
  NowPlayingHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _NowPlayingHomePageState createState() => _NowPlayingHomePageState();
}

class _NowPlayingHomePageState extends State<NowPlayingHomePage> {
  final _api = TMDBApi();
  List<Movie> _movies = <Movie>[];
  Movie _currentMovie;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();

    _api.getNowPlaying(context).then((value) {
      _movies = value.results;
      setState(() {
        if (_movies.length > 0) {
          _currentMovie = _movies[0];
        }
      });
    });
  }

  Widget _buildListWidgets() {
    return ListView.builder(
      itemBuilder: (BuildContext context, int index) => MovieTile(
        movie: _movies[index],
        onTap: _onMovieTap,
      ),
      itemCount: _movies.length,
    );
  }

  /// Function to call when a [Movie] is tapped.
  void _onMovieTap(Movie movie) {
    setState(() {
      _currentMovie = movie;
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

    final listView = _buildListWidgets();

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Padding(
        padding: paddingAll_8,
        child: Container(
          width: double.infinity,
          child: Column(
            children: <Widget>[
              header,
              Expanded(
                child: listView,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
