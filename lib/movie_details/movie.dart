import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/strings.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';

final _dateFormat = DateFormat.yMMMd();
final _currencyFormat = NumberFormat.simpleCurrency(name: "USD");
final _timeFormat = DateFormat.Hm();

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
    final labelStyle = textTheme.subtitle1;
    final gone = Container();

    final titleWidget = Text(
      movie.title,
      maxLines: 2,
      style: textTheme.headline5,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageLabel = Text(
      R.string.vote_average_label,
      style: labelStyle,
    );

    final voteAverageWidget =
        LinearProgressIndicator(value: movie.voteAverage / 10.0);

    final hasRuntime = movie.runtime != null;

    final runtimeMargin = hasRuntime ? SizedBox(height: padding_8) : gone;

    final runtimeLabel = hasRuntime
        ? Text(
            R.string.runtime_label,
            style: labelStyle,
          )
        : gone;

    final runtimeWidget = hasRuntime
        ? Text(_timeFormat.format(DateTime.utc(0, 1, 1, 0, movie.runtime)))
        : gone;

    final hasBudget = movie.budget != null;

    final budgetMargin = hasBudget ? SizedBox(height: padding_8) : gone;

    final budgetLabel = hasBudget
        ? Text(
            R.string.budget_label,
            style: labelStyle,
          )
        : gone;

    final budgetWidget =
        hasBudget ? Text(_currencyFormat.format(movie.budget)) : gone;

    final hasRevenue = movie.revenue != null;

    final revenueMargin = hasRevenue ? SizedBox(height: padding_8) : gone;

    final revenueLabel = hasRevenue
        ? Text(
            R.string.revenue_label,
            style: labelStyle,
          )
        : gone;

    final revenueWidget =
        hasRevenue ? Text(_currencyFormat.format(movie.revenue)) : gone;

    final dateMargin = SizedBox(height: padding_8);

    final dateLabel = Text(
      R.string.release_date_label,
      style: labelStyle,
    );

    final dateWidget = Text(_dateFormat.format(movie.releaseDate));

    final summaryLabel = Text(
      R.string.summary_label,
      style: labelStyle,
    );

    final summaryWidget = Text(
      movie.overview,
      maxLines: 100,
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
                  padding: paddingLeft_16,
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      voteAverageLabel,
                      voteAverageWidget,
                      dateMargin,
                      dateLabel,
                      dateWidget,
                      runtimeMargin,
                      runtimeLabel,
                      runtimeWidget,
                      budgetMargin,
                      budgetLabel,
                      budgetWidget,
                      revenueMargin,
                      revenueLabel,
                      revenueWidget,
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
