import 'package:flutter/material.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/media_cast.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';

import 'cast_tile.dart';

class CastList extends StatelessWidget {
  final MovieDetails movie;
  final ValueChanged<MediaCast> onTap;

  const CastList({
    super.key,
    required this.movie,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<CreditsResponse>(
      future: _fetchCast(context),
      builder: (BuildContext context, AsyncSnapshot<CreditsResponse> snapshot) {
        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            List<MediaCast> cast = snapshot.data?.cast ?? [];

            content = SizedBox(
              height: castTileHeight + 100,
              child: ListView(
                scrollDirection: Axis.horizontal,
                children: _buildCastList(context, cast),
              ),
            );
          } else {
            content = Container();
          }
        } else {
          content = const Center(child: CircularProgressIndicator());
        }

        return content;
      },
    );
  }

  Future<CreditsResponse> _fetchCast(BuildContext context) async {
    final TMDBApi api = InjectorWidget.get(context).api;
    CreditsResponse? credits = movie.credits;
    if (credits != null) return credits;
    return api.getMovieCredits(context, movie);
  }

  List<Widget> _buildCastList(BuildContext context, List<MediaCast> cast) {
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
