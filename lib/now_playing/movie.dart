import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';

final _dateFormat = DateFormat.yMMMd();

class MovieWidget extends StatelessWidget {
  final Movie movie;

  const MovieWidget({Key key, @required this.movie})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final imageWidth = posterWidth;
    final imageHeight = posterHeight;
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
      style: textTheme.headline6,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageWidget =
        LinearProgressIndicator(value: movie.voteAverage / 10.0);

    final dateWidget = Text(
      _dateFormat.format(movie.releaseDate),
      maxLines: 1,
    );

    final summaryWidget = Text(
      movie.overview,
      maxLines: 5,
      overflow: TextOverflow.ellipsis,
    );

    return Card(
      child: InkWell(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Row(
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
                      titleWidget,
                      SizedBox(height: padding_8),
                      voteAverageWidget,
                      SizedBox(height: padding_8),
                      dateWidget,
                      SizedBox(height: padding_8),
                      summaryWidget,
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
