import 'package:flutter/material.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

import 'movie_grid_tile.dart';

abstract class MoviesGridPage extends StatefulWidget {
  final String title;
  final ValueChanged<MoviesGridPage> onListIconTap;

  MoviesGridPage({Key key, this.title, this.onListIconTap}) : super(key: key);
}

abstract class MoviesGridState<P extends MoviesGridPage> extends State<P> {
  final TMDBApi _api = TMDBApi();

  //TODO can add listener to controller to load next page
  ScrollController _scrollController;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController();
  }

  Widget _buildGridWidgets(List<Movie> movies) {
    final media = MediaQuery.of(context);
    final screenSize = media.size;
    final screenWidth = screenSize.width;
    final cellWidth = padding_8 + posterGridWidth + padding_8;
    final columnCount = screenWidth ~/ cellWidth;
    final columnWidth = screenWidth / columnCount;

    return GridView.builder(
      controller: _scrollController,
      itemBuilder: (BuildContext context, int index) => MovieGridTile(
        movie: movies[index],
        onTap: _onMovieTap,
        width: columnWidth,
      ),
      itemCount: movies.length,
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: columnCount,
        childAspectRatio: 0.485,
      ),
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
            content = _buildGridWidgets(snapshot.data.results);
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

        var actions = <Widget>[];

        if (widget.onListIconTap != null) {
          final iconToList = IconButton(
            icon: Icon(MdiIcons.viewList),
            onPressed: () => widget.onListIconTap(widget),
          );
          actions.add(iconToList);
        }

        return Scaffold(
          appBar: AppBar(
            title: Text(string.title),
            actions: actions,
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
