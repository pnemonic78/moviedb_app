import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_parallax/flutter_parallax.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating/smooth_star_rating.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

final _dateFormat = DateFormat.yMMMd();
const _parallaxFactor = 0.85;

class MovieListTile extends StatelessWidget {
  final Movie movie;
  final ValueChanged<Movie> onTap;

  const MovieListTile({
    Key key,
    @required this.movie,
    @required this.onTap,
  })  : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final thumbnailWidth = posterListWidth;
    final thumbnailHeight = posterListHeight;
    final imageWidth = thumbnailWidth;
    final imageHeight = thumbnailHeight / _parallaxFactor;

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
    final thumbnailWidget = ClipRRect(
      borderRadius: borderCircular_8,
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

    final summaryWidget = Text(
      movie.overview,
      maxLines: 4,
      overflow: TextOverflow.ellipsis,
    );

    final marginTop = SizedBox(height: padding_8);

    final card = Card(
      child: InkWell(
        onTap: onTap == null ? null : () => onTap(movie),
        child: Padding(
          padding: paddingAll_8,
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              thumbnailWidget,
              SizedBox(width: padding_8),
              Expanded(
                child: Column(
                  mainAxisSize: MainAxisSize.max,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    titleWidget,
                    marginTop,
                    voteAverageWidget,
                    marginTop,
                    dateWidget,
                    marginTop,
                    summaryWidget,
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );

    return card;
  }
}
