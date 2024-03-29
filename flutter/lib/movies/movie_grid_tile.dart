import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating_null_safety/smooth_star_rating_null_safety.dart';
import 'package:tmdb/parallax/parallax_image.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';

final _dateFormat = DateFormat.yMMMd();
const _parallaxFactor = 0.85;

class MovieGridTile extends StatelessWidget {
  final Movie movie;
  final ValueChanged<Movie> onTap;
  final double width;

  const MovieGridTile({
    super.key,
    required this.movie,
    required this.onTap,
    this.width = posterGridWidth,
  });

  @override
  Widget build(BuildContext context) {
    final thumbnailWidth = width;
    final thumbnailHeight = thumbnailWidth * 1.5;
    final imageWidth = thumbnailWidth;
    final imageHeight = thumbnailHeight / _parallaxFactor;

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
      maxLines: 2,
      style: textTheme.titleMedium,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageWidgetPadding = paddingHorizontal_8.horizontal * 2;
    final voteAverageWidget = SmoothStarRating(
      rating: movie.voteAverage / 2.0,
      color: Colors.yellow,
      borderColor: Colors.yellow,
      size: (width - voteAverageWidgetPadding) / 7,
    );

    final dateValue = movie.releaseDate;
    final dateWidget = Text(
      (dateValue != null) ? _dateFormat.format(dateValue) : "",
      maxLines: 1,
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
              Padding(padding: paddingHorizontal_8, child: voteAverageWidget),
              Padding(padding: paddingHorizontal_8, child: titleWidget),
              Padding(padding: paddingHorizontal_8, child: dateWidget),
            ],
          ),
        ),
      ),
    );
  }
}
