import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating/smooth_star_rating.dart';
import 'package:tmdb/credits/cast_list.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/media_cast.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:tmdb/videos/videos_list.dart';

final _dateFormat = DateFormat.yMMMd();
final _currencyFormat = NumberFormat.simpleCurrency(name: "USD");
final _timeFormat = DateFormat.Hm();

final _summaryLinesMin = 5;
final _summaryLinesMax = 1000;

class MovieDetailsWidget extends StatefulWidget {
  final MovieDetails movie;
  final ValueChanged<MovieDetails> onTapPoster;
  final ValueChanged<MovieVideo> onVideoTap;
  final ValueChanged<MediaCast> onCastTap;

  const MovieDetailsWidget({
    Key key,
    @required this.movie,
    this.onTapPoster,
    this.onVideoTap,
    this.onCastTap,
  })  : assert(movie != null),
        super(key: key);

  @override
  _MovieDetailsWidgetState createState() => _MovieDetailsWidgetState();
}

class _MovieDetailsWidgetState extends State<MovieDetailsWidget> {
  bool _summaryLinesExpanded;
  VideosList _videoList;
  CastList _castList;

  @override
  void initState() {
    super.initState();
    _summaryLinesExpanded = false;
  }

  Widget _buildVideos(MovieDetails movie) {
    if (_videoList == null) {
      _videoList = VideosList(
        movie: movie,
        onTap: widget.onVideoTap,
      );
    }
    return _videoList;
  }

  Widget _buildCast(MovieDetails movie) {
    if (_castList == null) {
      _castList = CastList(
        movie: movie,
        onTap: widget.onCastTap,
      );
    }
    return _castList;
  }

  @override
  Widget build(BuildContext context) {
    final movie = widget.movie;

    final textTheme = Theme.of(context).textTheme;
    final labelStyle = textTheme.headline6;
    final textStyle = textTheme.bodyText2.apply(fontSizeFactor: 1.25);
    final gone = Container();
    final string = AppLocalizations.of(context);

    final media = MediaQuery.of(context);
    final imageWidth = posterDetailsWidth;
    final imageHeight = posterDetailsHeight;
    final posterUrl = TMDBApi.generatePosterUrl(
      movie.posterPath,
      imageWidth,
      imageHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final poster = (posterUrl != null)
        ? CachedNetworkImage(
            imageUrl: posterUrl,
            placeholder: (context, url) => Icon(
              Icons.image,
              size: min(imageWidth, imageHeight),
            ),
            width: imageWidth,
            height: imageHeight,
            fit: BoxFit.fitHeight,
          )
        : Container(
            width: imageWidth,
            height: imageHeight,
          );
    final posterWidget = InkWell(
      child: ClipRRect(
        borderRadius: borderCircular_8,
        child: poster,
      ),
      onTap: () => widget.onTapPoster(movie),
    );

    final taglineWidget = Text(
      movie.tagline ?? "",
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
      style: textStyle,
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
        ? Text(
            _timeFormat.format(DateTime.utc(0, 1, 1, 0, movie.runtime)),
            style: textStyle,
          )
        : gone;

    final hasBudget = (movie.budget != null) && (movie.budget > 0);

    final budgetMargin = hasBudget ? SizedBox(height: padding_8) : gone;

    final budgetLabel = hasBudget
        ? Text(
            string.budget_label,
            style: labelStyle,
          )
        : gone;

    final budgetWidget = hasBudget
        ? Text(
            _currencyFormat.format(movie.budget),
            style: textStyle,
          )
        : gone;

    final hasRevenue = (movie.revenue != null) && (movie.revenue > 0);

    final revenueMargin = hasRevenue ? SizedBox(height: padding_8) : gone;

    final revenueLabel = hasRevenue
        ? Text(
            string.revenue_label,
            style: labelStyle,
          )
        : gone;

    final revenueWidget = hasRevenue
        ? Text(
            _currencyFormat.format(movie.revenue),
            style: textStyle,
          )
        : gone;

    final dateMargin = SizedBox(height: padding_8);

    final dateLabel = Text(
      string.release_date_label,
      style: labelStyle,
    );

    final dateWidget = Text(
      _dateFormat.format(movie.releaseDate),
      style: textStyle,
    );

    final summaryMargin = SizedBox(height: padding_8);

    final summaryLabel = Text(
      string.summary_label,
      style: labelStyle,
    );

    final summaryText = Text(
      movie.overview ?? "",
      maxLines: (_summaryLinesExpanded ? _summaryLinesMax : _summaryLinesMin),
      overflow: TextOverflow.fade,
      style: textStyle,
    );

    final summaryWidget = InkWell(
      child: summaryText,
      onTap: () {
        setState(() {
          _summaryLinesExpanded = !_summaryLinesExpanded;
        });
      },
    );

    final hasGenres = (movie.genres != null) && movie.genres.isNotEmpty;

    final genresMargin = hasGenres ? SizedBox(height: padding_8) : gone;

    final genresLabel = hasGenres
        ? Text(
            string.genres_label,
            style: labelStyle,
          )
        : gone;

    final genresWidget = hasGenres
        ? Text(
            movie.genres.join(", "),
            style: textStyle,
          )
        : gone;

    final videosLabel = Text(
      string.videos_label,
      style: labelStyle,
    );

    final videosMargin = SizedBox(height: padding_8);

    final videosWidget = _buildVideos(movie);

    final castLabel = Text(
      string.cast_label,
      style: labelStyle,
    );

    final castMargin = SizedBox(height: padding_8);

    final castWidget = _buildCast(movie);

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
          videosLabel,
          videosWidget,
          castMargin,
          castLabel,
          castWidget,
        ],
      ),
    );

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Padding(padding: paddingAll_8, child: taglineWidget),
        details,
      ],
    );
  }
}
