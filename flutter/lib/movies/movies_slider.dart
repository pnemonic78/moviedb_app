import 'package:flutter/material.dart';
import 'package:tmdb/movies/movies_all_tile.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

class MoviesSlider extends StatelessWidget {
  final List<Movie> movies;
  final ValueChanged<Movie> onTap;

  const MoviesSlider({super.key, required this.movies, required this.onTap});

  @override
  Widget build(BuildContext context) {
    final tile =
        movies.isEmpty ? null : MoviesAllTile(movie: movies[0], onTap: onTap);
    final tileHeight = tile?.height(context) ?? thumbnailHeight;

    return SizedBox(
      height: tileHeight,
      child: movies.isEmpty
          ? const Center(child: CircularProgressIndicator())
          : ListView.builder(
              scrollDirection: Axis.horizontal,
              itemBuilder: _buildItem,
            ),
    );
  }

  Widget? _buildItem(BuildContext context, int index) {
    return MoviesAllTile(
      movie: movies[index],
      onTap: onTap,
    );
  }
}
