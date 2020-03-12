import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie.dart';

const _posterHeight = 150.0;
const _posterWidth = _posterHeight * 0.665;

const _padding = 8.0;

const _paddingLeft = const EdgeInsets.only(left: _padding);
const _paddingTop = const EdgeInsets.only(top: _padding);

final dateFormat = DateFormat.yMMMd();

class MovieWidget extends StatelessWidget {
  final Movie movie;

  const MovieWidget({Key key, @required this.movie})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final imageWidth = _posterWidth;
    final imageHeight = _posterHeight;
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
      dateFormat.format(movie.releaseDate),
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
                  padding: _paddingLeft,
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      titleWidget,
                      SizedBox(height: _padding),
                      voteAverageWidget,
                      SizedBox(height: _padding),
                      dateWidget,
                      SizedBox(height: _padding),
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
