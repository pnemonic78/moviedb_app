import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating_null_safety/smooth_star_rating_null_safety.dart';
import 'package:tmdb/parallax/parallax_image.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

final _dateFormat = DateFormat.yMMMd();
const _parallaxFactor = 0.85;

class MovieListTile extends StatelessWidget {
  final Movie movie;
  final ValueChanged<Movie> onTap;

  const MovieListTile({
    super.key,
    required this.movie,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    const thumbnailWidth = posterListWidth;
    const thumbnailHeight = posterListHeight;
    const imageWidth = thumbnailWidth;
    const imageHeight = thumbnailHeight / _parallaxFactor;

    final thumbnail = TMDBApi.generatePoster(
      posterPath: movie.posterPath,
      posterWidth: imageWidth,
      posterHeight: imageHeight,
    );
    final thumbnailWidget = ClipRRect(
      borderRadius: borderCircular_8,
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
      maxLines: 2,
      style: textTheme.titleLarge,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageWidget = SmoothStarRating(
      rating: movie.voteAverage / 2.0,
      color: Colors.yellow,
      borderColor: Colors.yellow.shade600,
    );

    final dateValue = movie.releaseDate;
    final dateWidget = Text(
      (dateValue != null) ? _dateFormat.format(dateValue) : "",
      maxLines: 1,
    );

    final summaryWidget = Text(
      movie.overview ?? "",
      maxLines: 4,
      overflow: TextOverflow.ellipsis,
    );

    const marginTop = SizedBox(height: padding_8);

    final card = Card(
      child: InkWell(
        onTap: () => onTap(movie),
        child: Padding(
          padding: paddingAll_8,
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              thumbnailWidget,
              const SizedBox(width: padding_8),
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
