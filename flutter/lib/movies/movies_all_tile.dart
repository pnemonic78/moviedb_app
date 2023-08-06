import 'package:flutter/material.dart';
import 'package:tmdb/movies/movie_grid_tile.dart';
import 'package:tmdb/parallax/parallax_image.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';

const maxLinesMoviesAllTile = 2;

class MoviesAllTile extends MovieGridTile {
  const MoviesAllTile({
    super.key,
    required super.movie,
    required super.onTap,
    super.width = posterGridWidth,
  });

  @override
  Widget build(BuildContext context) {
    final thumbnailWidth = width;
    final thumbnailHeight = thumbnailWidth * 1.5;
    final imageWidth = thumbnailWidth;
    final imageHeight = thumbnailHeight;

    final thumbnail = TMDBApi.generatePoster(
      posterPath: movie.posterPath,
      posterWidth: imageWidth,
      posterHeight: imageHeight,
    );
    final thumbnailWidget = ClipPath.shape(
      // rounded rectangle crop for top side only.
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.vertical(top: cardRadius),
      ),
      child: SizedBox(
        width: thumbnailWidth,
        height: thumbnailHeight,
        child: ParallaxImage(
          child: thumbnail,
        ),
      ),
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      movie.title,
      maxLines: maxLinesMoviesAllTile,
      style: textTheme.titleMedium,
      overflow: TextOverflow.ellipsis,
    );

    return Card(
      child: InkWell(
        onTap: () => onTap(movie),
        child: SizedBox(
          width: width,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              thumbnailWidget,
              Padding(padding: paddingHorizontal_8, child: titleWidget),
            ],
          ),
        ),
      ),
    );
  }
}
