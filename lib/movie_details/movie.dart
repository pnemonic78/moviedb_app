import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/strings.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';

final _dateFormat = DateFormat.yMMMd();

class MovieDetailsWidget extends StatelessWidget {
  final MovieDetails movie;

  const MovieDetailsWidget({Key key, @required this.movie})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final imageWidth = posterDetailsWidth;
    final imageHeight = posterDetailsHeight;
    final path =
        TMDBApi.generatePosterUrl(movie.posterPath, imageWidth, imageHeight);
    final poster = Image.network(
      path,
      width: imageWidth,
      height: imageHeight,
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      movie.title,
      maxLines: 2,
      style: textTheme.headline5,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageLabel = Text(
      R.string.vote_average_label,
      style: textTheme.subtitle1,
    );

    final voteAverageWidget =
        LinearProgressIndicator(value: movie.voteAverage / 10.0);

    final dateLabel = Text(
      R.string.release_date_label,
      style: textTheme.subtitle1,
    );

    final dateWidget = Text(
      _dateFormat.format(movie.releaseDate),
      maxLines: 1,
    );

    final summaryLabel = Text(
      R.string.summary_label,
      style: textTheme.subtitle1,
    );

    final summaryWidget = Text(
      movie.overview,
      maxLines: 5,
      overflow: TextOverflow.ellipsis,
    );

    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          titleWidget,
          SizedBox(height: padding_8),
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              poster,
              Expanded(
                child: Padding(
                  padding: paddingLeft_8,
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      voteAverageLabel,
                      voteAverageWidget,
                      SizedBox(height: padding_8),
                      dateLabel,
                      dateWidget,
                    ],
                  ),
                ),
              ),
            ],
          ),
          SizedBox(height: padding_8),
          summaryLabel,
          summaryWidget,
        ],
      ),
    );
  }
}
