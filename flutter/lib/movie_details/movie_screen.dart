import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:smooth_star_rating_null_safety/smooth_star_rating_null_safety.dart';
import 'package:tmdb/credits/cast_list.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/model/media_cast.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:tmdb/videos/videos_list.dart';

import '../tmdb_api/api.dart';

final _dateFormat = DateFormat.yMMMd();
final _currencyFormat = NumberFormat.simpleCurrency(name: "USD");
final _timeFormat = DateFormat.Hm();

const _summaryLinesMin = 5;
const _summaryLinesMax = 1000;

class MovieDetailsWidget extends StatefulWidget {
  final MovieDetails movie;
  final ValueChanged<MovieDetails> onTapPoster;
  final ValueChanged<MovieVideo> onVideoTap;
  final ValueChanged<MediaCast> onCastTap;

  const MovieDetailsWidget({
    super.key,
    required this.movie,
    required this.onTapPoster,
    required this.onVideoTap,
    required this.onCastTap,
  });

  @override
  State<MovieDetailsWidget> createState() => _MovieDetailsWidgetState();
}

class _MovieDetailsWidgetState extends State<MovieDetailsWidget> {
  bool _summaryLinesExpanded = false;
  VideosList? _videoList;
  CastList? _castList;

  @override
  void initState() {
    super.initState();
    _summaryLinesExpanded = false;
  }

  Widget _buildVideos(MovieDetails movie) {
    VideosList? videoList = _videoList;
    if (videoList == null) {
      videoList = VideosList(
        movie: movie,
        onTap: widget.onVideoTap,
      );
      _videoList = videoList;
    }
    return videoList;
  }

  Widget _buildCast(MovieDetails movie) {
    CastList? castList = _castList;
    if (castList == null) {
      castList = CastList(
        movie: movie,
        onTap: widget.onCastTap,
      );
      _castList = castList;
    }
    return castList;
  }

  @override
  Widget build(BuildContext context) {
    final movie = widget.movie;

    final textTheme = Theme.of(context).textTheme;
    final labelStyle = textTheme.titleLarge;
    final textStyle = textTheme.bodyLarge;
    final gone = Container();
    final string = AppLocalizations.of(context)!;

    const imageWidth = posterDetailsWidth;
    const imageHeight = posterDetailsHeight;
    final poster = TMDBApi.generatePoster(
      posterPath: movie.posterPath,
      posterWidth: imageWidth,
      posterHeight: imageHeight,
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
      style: textTheme.titleMedium,
      overflow: TextOverflow.ellipsis,
    );

    final voteAverageLabel = Text(
      string.vote_average_label,
      style: labelStyle,
    );

    final voteAverageRating = SmoothStarRating(
      rating: movie.voteAverage / 2.0,
      color: Colors.yellow,
      borderColor: Colors.yellow,
    );

    final voteAverageWidget = Text(
      NumberFormat.decimalPercentPattern(decimalDigits: 0)
          .format(movie.voteAverage / 10),
      style: textStyle,
    );

    final hasRuntime = movie.runtime > 0;

    final runtimeMargin = hasRuntime ? const SizedBox(height: padding_8) : gone;

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

    final hasBudget = (movie.budget > 0);

    final budgetMargin = hasBudget ? const SizedBox(height: padding_8) : gone;

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

    final hasRevenue = (movie.revenue > 0);

    final revenueMargin = hasRevenue ? const SizedBox(height: padding_8) : gone;

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

    const dateMargin = SizedBox(height: padding_8);

    final hasDate = (movie.releaseDate != null);

    final dateLabel = hasDate
        ? Text(
            string.release_date_label,
            style: labelStyle,
          )
        : gone;

    final dateWidget = hasDate
        ? Text(
            _dateFormat.format(movie.releaseDate!),
            style: textStyle,
          )
        : gone;

    const summaryMargin = SizedBox(height: padding_8);

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

    final hasGenres = movie.genres.isNotEmpty;

    final genresMargin = hasGenres ? const SizedBox(height: padding_8) : gone;

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

    const videosMargin = SizedBox(height: padding_8);

    final videosWidget = _buildVideos(movie);

    final castLabel = Text(
      string.cast_label,
      style: labelStyle,
    );

    final castMargin = const SizedBox(height: padding_8);

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
              const SizedBox(width: padding_16),
              Expanded(
                child: Column(
                  mainAxisSize: MainAxisSize.max,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    voteAverageLabel,
                    Row(
                      children: <Widget>[
                        voteAverageRating,
                        voteAverageWidget,
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
