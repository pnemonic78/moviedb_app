import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating_null_safety/smooth_star_rating_null_safety.dart';
import 'package:tmdb/parallax/parallax_image.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/utils/flutter_ui.dart';

final _dateFormat = DateFormat.yMMMd();
const _parallaxFactor = 0.85;
const _titleMaxLines = 2;

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
    final thumbnailHeight = thumbnailWidth * ratioPoster;
    final imageWidth = thumbnailWidth;
    final imageHeight = thumbnailHeight / _parallaxFactor;

    final thumbnail = TMDBApi.generatePoster(
      posterPath: movie.posterPath,
      posterWidth: imageWidth,
      posterHeight: imageHeight,
    );
    final thumbnailWidget = ClipRRect(
      borderRadius: borderCircularTop_8,
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
      "${movie.title}\n\n",
      maxLines: 2,
      style: textTheme.titleMedium,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageWidgetPadding = paddingHorizontal_8.horizontal * 2;
    final voteAverageWidget = SmoothStarRating(
      rating: movie.voteAverage / 2.0,
      color: Colors.amber,
      borderColor: Colors.amber.shade600,
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
            children: [
              thumbnailWidget,
              Padding(padding: paddingHorizontal_8, child: voteAverageWidget),
              Padding(padding: paddingHorizontal_8, child: titleWidget),
              Padding(padding: paddingBelow_8, child: dateWidget),
            ],
          ),
        ),
      ),
    );
  }

  double _height(BuildContext context, double width) {
    final thumbnailWidth = width;
    final thumbnailHeight = thumbnailWidth * ratioPoster;
    final voteAverageWidgetHeight = 25;
    final textTheme = Theme.of(context).textTheme;
    final titleWidgetHeight =
        textSize("\n\n", textTheme.titleMedium!, _titleMaxLines).height;
    final dateWidgetHeight =
        textSize("AD gy 0000", textTheme.titleSmall!, 1).height;
    return thumbnailHeight +
        voteAverageWidgetHeight +
        titleWidgetHeight +
        dateWidgetHeight +
        paddingBelow_8.bottom +
        8;
  }

  Size size(BuildContext context) {
    return Size(width, _height(context, width));
  }
}
