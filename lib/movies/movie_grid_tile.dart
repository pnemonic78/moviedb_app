import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating/smooth_star_rating.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

final _dateFormat = DateFormat.yMMMd();

class MovieGridTile extends StatelessWidget {
  final Movie movie;
  final ValueChanged<Movie> onTap;
  final double width;

  const MovieGridTile({
    Key key,
    @required this.movie,
    @required this.onTap,
    this.width = posterGridWidth,
  })  : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final imageWidth = width;
    final imageHeight = imageWidth * 1.5;
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
        side: BorderSide(color: Colors.blue),
        borderRadius: BorderRadius.vertical(top: cardRadius),
      ),
      child: thumbnail,
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      movie.title,
      maxLines: 1,
      style: textTheme.headline6,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageWidget = SmoothStarRating(
      rating: movie.voteAverage / 2.0,
      isReadOnly: true,
      color: Colors.yellow,
      borderColor: Colors.yellow,
    );

    final dateWidget = Text(
      _dateFormat.format(movie.releaseDate),
      maxLines: 1,
    );

    final card = Card(
      child: InkWell(
        onTap: onTap == null ? null : () => onTap(movie),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            thumbnailWidget,
            Padding(padding: paddingHorizontal_8, child: voteAverageWidget),
            Padding(padding: paddingHorizontal_8, child: titleWidget),
            Padding(padding: paddingHorizontal_8, child: dateWidget),
          ],
        ),
      ),
    );

    return card;
  }
}
