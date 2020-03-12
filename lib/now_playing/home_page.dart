import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/strings.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';

import 'movie.dart';

class NowPlayingHomePage extends StatefulWidget {
  NowPlayingHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _NowPlayingHomePageState createState() => _NowPlayingHomePageState();
}

class _NowPlayingHomePageState extends State<NowPlayingHomePage> {
  final _api = TMDBApi();
  final _movieWidgets = <Widget>[];
  Movie _currentMovie;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();

    final List<Movie> movies = _api.getMovies();
    _movieWidgets.clear();
    for (var i = 0; i < movies.length; i++) {
      _movieWidgets.add(MovieWidget(
        movie: movies[i],
        onTap: _onMovieTap,
      ));
    }
  }

  Widget _buildListWidgets() {
    return ListView.builder(
      itemBuilder: (BuildContext context, int index) => _movieWidgets[index],
      itemCount: _movieWidgets.length,
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
