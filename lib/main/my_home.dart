import 'package:flutter/material.dart';
import 'package:tmdb/movies/movies_grid_page.dart';
import 'package:tmdb/movies/movies_list_page.dart';
import 'package:tmdb/now_playing/now_playing_grid.dart';
import 'package:tmdb/now_playing/now_playing_list.dart';

class MyHome extends StatefulWidget {
  MyHome({Key key}) : super(key: key);

  @override
  _MyHomeState createState() => _MyHomeState();
}

class _MyHomeState extends State<MyHome> {
  Widget _page;

  @override
  Widget build(BuildContext context) {
    return _getPage();
  }

  Widget _getPage() {
    if (_page == null) {
      _page = NowPlayingGridPage(onListIconTap: _onListIconTap);
    }
    return _page;
  }

  _onGridIconTap(MoviesListPage page) {
    setState(() {
      _page = NowPlayingGridPage(onListIconTap: _onListIconTap);
    });
  }

  _onListIconTap(MoviesGridPage page) {
    setState(() {
      _page = NowPlayingListPage(onGridIconTap: _onGridIconTap);
    });
  }
}
