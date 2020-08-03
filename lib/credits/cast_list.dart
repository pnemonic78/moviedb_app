import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/cast.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

import 'cast_tile.dart';

class CastList extends StatelessWidget {
  final Movie movie;
  final ValueChanged<MovieCast> onTap;
  final TMDBApi _api = TMDBApi();

  CastList({Key key, @required this.movie, this.onTap})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<CreditsResponse>(
      future: _fetchCast(context),
      builder: (BuildContext context, AsyncSnapshot<CreditsResponse> snapshot) {
        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            content = Container(
              child: ListView(
                scrollDirection: Axis.horizontal,
                children: _buildCastList(context, snapshot.data.cast),
              ),
              height: castTileHeight + 100,
            );
          } else {
            content = Container();
          }
        } else {
          content = Center(child: CircularProgressIndicator());
        }

        return content;
      },
    );
  }

  Future<CreditsResponse> _fetchCast(BuildContext context) async {
    return _api.getMovieCredits(context, movie);
  }

  List<Widget> _buildCastList(BuildContext context, List<MovieCast> cast) {
    final list = <Widget>[];
    cast.sort((a, b) => a.order.compareTo(b.order));

    for (var member in cast) {
      list.add(CastTile(
        cast: member,
        onTap: onTap,
      ));
    }

    return list;
  }
}
