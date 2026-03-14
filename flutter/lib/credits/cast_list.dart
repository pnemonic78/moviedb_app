import 'package:flutter/material.dart';
import 'package:tmdb/di/injector_inherited.dart';
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
            if (cast.isEmpty) {
              content = Container();
            } else {
              cast.sort((a, b) => a.order.compareTo(b.order));
              final tile = CastTile(
                cast: cast[0],
                onTap: onTap,
              );
              final tileHeight = tile.height(context);

              content = SizedBox(
                height: tileHeight,
                child: ListView.builder(
                  scrollDirection: Axis.horizontal,
                  itemCount: cast.length,
                  itemBuilder: (BuildContext context, int index) => CastTile(
                    cast: cast[index],
                    onTap: onTap,
                  ),
                ),
              );
            }
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
}
