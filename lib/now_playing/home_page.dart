import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/movie.dart';

import 'movie.dart';

class NowPlayingHomePage extends StatefulWidget {
  NowPlayingHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _NowPlayingHomePageState createState() => _NowPlayingHomePageState();
}

class _NowPlayingHomePageState extends State<NowPlayingHomePage> {
  final _movies = <Movie>[];
  final _movieWidgets = <Widget>[];

  @override
  void initState() {
    super.initState();

    _movies.clear();
    for (var i = 1; i <= 10; i++) {
      _movies.add(Movie(id: i, title: 'Movie #$i'));
    }
  }

  @override
  void didUpdateWidget(NowPlayingHomePage oldWidget) {
    super.didUpdateWidget(oldWidget);

    _movieWidgets.clear();
    for (var i = 0; i < _movies.length; i++) {
      _movieWidgets.add(MovieWidget(movie: _movies[i]));
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
