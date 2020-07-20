import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating/smooth_star_rating.dart';
import 'package:tmdb/movie_details/videos_list.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';
import 'package:tmdb/tmdb_api/video.dart';

final _dateFormat = DateFormat.yMMMd();
final _currencyFormat = NumberFormat.simpleCurrency(name: "USD");
final _timeFormat = DateFormat.Hm();

class MovieDetailsWidget extends StatelessWidget {
  final MovieDetails movie;
  final ValueChanged<String> onPosterTap;
  final ValueChanged<MovieVideo> onVideoTap;

  const MovieDetailsWidget(
      {Key key, @required this.movie, this.onPosterTap, this.onVideoTap})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final screenSize = MediaQuery.of(context).size;
    final screenWidth = screenSize.width;
    final screenHeight = screenSize.height;

    final backdropWidth = screenWidth;
    final backdropHeight = backdropWidth * 9 / 16;
    final backdropUrl = TMDBApi.generatePosterUrl(
        movie.backdropPath, backdropWidth, backdropHeight);
    final backdrop = CachedNetworkImage(
      imageUrl: backdropUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(backdropWidth, backdropHeight),
      ),
      width: backdropWidth,
      height: backdropHeight,
    );
    final backdropWidget = backdrop;

    final imageWidth = posterDetailsWidth;
    final imageHeight = posterDetailsHeight;
    final posterUrl =
        TMDBApi.generatePosterUrl(movie.posterPath, imageWidth, imageHeight);
    final poster = CachedNetworkImage(
      imageUrl: posterUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
    );
    final posterWidget = InkWell(
      child: ClipRRect(
        borderRadius: borderCircular_8,
        child: poster,
      ),
      onTap: () => onPosterTap(movie.posterPath),
    );

    final textTheme = Theme.of(context).textTheme;
    final labelStyle = textTheme.subtitle1;
    final gone = Container();
    final string = AppLocalizations.of(context);

    final titleWidget = Text(
      movie.title,
      maxLines: 2,
      style: textTheme.headline4,
      overflow: TextOverflow.ellipsis,
    );

    final taglineWidget = Text(
      movie.tagline,
      maxLines: 2,
      style: textTheme.subtitle1,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageLabel = Text(
      string.vote_average_label,
      style: labelStyle,
    );

    final voteAverageWidget = SmoothStarRating(
      rating: movie.voteAverage / 2.0,
      isReadOnly: true,
      color: Colors.yellow,
      borderColor: Colors.yellow,
    );

    final voteAverageValue = Text(
      NumberFormat.decimalPercentPattern(decimalDigits: 0)
          .format(movie.voteAverage / 10),
      style: textTheme.subtitle1,
    );

    final hasRuntime = movie.runtime != null;

    final runtimeMargin = hasRuntime ? SizedBox(height: padding_8) : gone;

    final runtimeLabel = hasRuntime
        ? Text(
            string.runtime_label,
            style: labelStyle,
          )
        : gone;

    final runtimeWidget = hasRuntime
        ? Text(_timeFormat.format(DateTime.utc(0, 1, 1, 0, movie.runtime)))
        : gone;

    final hasBudget = (movie.budget != null) && (movie.budget > 0);

    final budgetMargin = hasBudget ? SizedBox(height: padding_8) : gone;

    final budgetLabel = hasBudget
        ? Text(
            string.budget_label,
            style: labelStyle,
          )
        : gone;

    final budgetWidget =
        hasBudget ? Text(_currencyFormat.format(movie.budget)) : gone;

    final hasRevenue = (movie.revenue != null) && (movie.revenue > 0);

    final revenueMargin = hasRevenue ? SizedBox(height: padding_8) : gone;

    final revenueLabel = hasRevenue
        ? Text(
            string.revenue_label,
            style: labelStyle,
          )
        : gone;

    final revenueWidget =
        hasRevenue ? Text(_currencyFormat.format(movie.revenue)) : gone;

    final dateMargin = SizedBox(height: padding_8);

    final dateLabel = Text(
      string.release_date_label,
      style: labelStyle,
    );

    final dateWidget = Text(_dateFormat.format(movie.releaseDate));

    final summaryMargin = SizedBox(height: padding_8);

    final summaryLabel = Text(
      string.summary_label,
      style: labelStyle,
    );

    final summaryWidget = Text(
      movie.overview,
      maxLines: 100,
      overflow: TextOverflow.ellipsis,
    );

    final hasGenres = (movie.genres != null) && movie.genres.isNotEmpty;

    final genresMargin = hasGenres ? SizedBox(height: padding_8) : gone;

    final genresLabel = hasGenres
        ? Text(
            string.genres_label,
            style: labelStyle,
          )
        : gone;

    final genresWidget = hasGenres ? Text(movie.genres.join(", ")) : gone;

    final videosMargin = SizedBox(height: padding_8);

    final header = Container(
      child: Stack(
        alignment: Alignment.bottomLeft,
        children: [
          backdropWidget,
          Container(
            color: Colors.black45,
            child: Padding(
              padding: paddingAll_8,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: <Widget>[
                  titleWidget,
                  taglineWidget,
                ],
              ),
            ),
          ),
        ],
      ),
    );

    final details = Padding(
      padding: paddingAll_8,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              posterWidget,
              SizedBox(width: padding_16),
              Expanded(
                child: Column(
                  mainAxisSize: MainAxisSize.max,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    voteAverageLabel,
                    Row(
                      children: <Widget>[
                        voteAverageWidget,
                        voteAverageValue,
                      ],
                    ),
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
                    genresMargin,
                    genresLabel,
                    genresWidget,
                  ],
                ),
              ),
            ],
          ),
          summaryMargin,
          summaryLabel,
          summaryWidget,
          videosMargin,
          VideosList(movie: movie, onTap: onVideoTap),
        ],
      ),
    );

    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          header,
          details,
        ],
      ),
    );
  }
}
