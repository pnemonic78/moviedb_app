import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_parallax/flutter_parallax.dart';
import 'package:tmdb/movies/movie_grid_tile.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

class MoviesAllTile extends MovieGridTile {
  const MoviesAllTile({
    Key key,
    @required Movie movie,
    @required ValueChanged<Movie> onTap,
    double width = posterGridWidth,
  })  : assert(movie != null),
        super(
          key: key,
          movie: movie,
          onTap: onTap,
          width: width,
        );

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final thumbnailWidth = width;
    final thumbnailHeight = thumbnailWidth * 1.5;
    final imageWidth = thumbnailWidth;
    final imageHeight = thumbnailHeight;

    final thumbnailUrl = TMDBApi.generatePosterUrl(
      movie.posterPath,
      imageWidth,
      imageHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final thumbnail = CachedNetworkImage(
      imageUrl: thumbnailUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
      fit: BoxFit.fitHeight,
    );
    final thumbnailWidget = ClipPath.shape(
      // rounded rectangle crop for top side only.
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.vertical(top: cardRadius),
      ),
      child: Container(
        width: thumbnailWidth,
        height: thumbnailHeight,
        child: Parallax.inside(
          child: thumbnail,
          mainAxisExtent: thumbnailHeight,
        ),
      ),
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      movie.title,
      maxLines: 2,
      style: textTheme.subtitle1,
      overflow: TextOverflow.ellipsis,
    );

    final card = Card(
      child: InkWell(
        onTap: onTap == null ? null : () => onTap(movie),
        child: Container(
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

    return card;
  }
}
