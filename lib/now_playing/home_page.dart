import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/movie.dart';
import 'package:tmdb/tmdb_api/movies.dart';

import 'movie.dart';

class NowPlayingHomePage extends StatefulWidget {
  NowPlayingHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _NowPlayingHomePageState createState() => _NowPlayingHomePageState();
}

class _NowPlayingHomePageState extends State<NowPlayingHomePage> {
  final _movieWidgets = <Widget>[];

  @override
  void didUpdateWidget(NowPlayingHomePage oldWidget) {
    super.didUpdateWidget(oldWidget);

    final List<Movie> movies = Movies.getMovies();
    _movieWidgets.clear();
    for (var i = 0; i < movies.length; i++) {
      _movieWidgets.add(MovieWidget(movie: movies[i]));
    }
  }

  Widget _buildListWidgets() {
    return ListView.builder(
      itemBuilder: (BuildContext context, int index) => _movieWidgets[index],
      itemCount: _movieWidgets.length,
    );
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    final header = Text(
      'Now Playing',
      style: theme.textTheme.headline6,
    );

    final listView = _buildListWidgets();

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Container(
          width: double.infinity,
          child: Column(
            //crossAxisAlignment: CrossAxisAlignment.start,
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
