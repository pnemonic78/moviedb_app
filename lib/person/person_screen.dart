import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

class PersonDetailsWidget extends StatelessWidget {
  final Person person;
  final ValueChanged<Person> onPosterTap;

  const PersonDetailsWidget({
    Key key,
    @required this.person,
    this.onPosterTap,
  })  : assert(person != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final imageWidth = castDetailsWidth;
    final imageHeight = castDetailsHeight;
    final posterUrl = TMDBApi.generatePosterUrl(
      person.profilePath,
      imageWidth,
      imageHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final poster = CachedNetworkImage(
      imageUrl: posterUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
      fit: BoxFit.contain,
    );
    final posterWidget = InkWell(
      child: ClipRRect(
        borderRadius: borderCircular_8,
        child: poster,
      ),
      onTap: () => onPosterTap(person),
    );

    final textTheme = Theme.of(context).textTheme;
    final groupStyle = textTheme.headline6;
    final labelStyle = textTheme.subtitle1;
    final gone = Container();
    final string = AppLocalizations.of(context);

    final nameWidget = Text(
      person.name,
      maxLines: 2,
      style: textTheme.headline4,
      overflow: TextOverflow.ellipsis,
    );

//    final voteAverageLabel = Text(
//      string.vote_average_label,
//      style: labelStyle,
//    );
//
//    final voteAverageWidget = SmoothStarRating(
//      rating: person.voteAverage / 2.0,
//      isReadOnly: true,
//      color: Colors.yellow,
//      borderColor: Colors.yellow,
//    );
//
//    final voteAverageValue = Text(
//      NumberFormat.decimalPercentPattern(decimalDigits: 0)
//          .format(person.voteAverage / 10),
//      style: textTheme.subtitle1,
//    );
//
//    final hasRuntime = person.runtime != null;
//
//    final runtimeMargin = hasRuntime ? SizedBox(height: padding_8) : gone;
//
//    final runtimeLabel = hasRuntime
//        ? Text(
//            string.runtime_label,
//            style: labelStyle,
//          )
//        : gone;
//
//    final runtimeWidget = hasRuntime
//        ? Text(_timeFormat.format(DateTime.utc(0, 1, 1, 0, person.runtime)))
//        : gone;
//
//    final hasBudget = (person.budget != null) && (person.budget > 0);
//
//    final budgetMargin = hasBudget ? SizedBox(height: padding_8) : gone;
//
//    final budgetLabel = hasBudget
//        ? Text(
//            string.budget_label,
//            style: labelStyle,
//          )
//        : gone;
//
//    final budgetWidget =
//        hasBudget ? Text(_currencyFormat.format(person.budget)) : gone;
//
//    final hasRevenue = (person.revenue != null) && (person.revenue > 0);
//
//    final revenueMargin = hasRevenue ? SizedBox(height: padding_8) : gone;
//
//    final revenueLabel = hasRevenue
//        ? Text(
//            string.revenue_label,
//            style: labelStyle,
//          )
//        : gone;
//
//    final revenueWidget =
//        hasRevenue ? Text(_currencyFormat.format(person.revenue)) : gone;
//
//    final dateMargin = SizedBox(height: padding_8);
//
//    final dateLabel = Text(
//      string.release_date_label,
//      style: labelStyle,
//    );
//
//    final dateWidget = Text(_dateFormat.format(person.releaseDate));
//
    final biographyMargin = SizedBox(height: padding_8);

    final biographyLabel = Text(
      string.biography_label,
      style: labelStyle,
    );

    final biographyWidget = Text(
      person.biography ?? "",
      maxLines: 1000,
      overflow: TextOverflow.ellipsis,
    );

    final details = Padding(
      padding: paddingAll_8,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          nameWidget,
          SizedBox(height: padding_8),
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              posterWidget,
              SizedBox(width: padding_16),
//              Expanded(
//                child: Column(
//                  mainAxisSize: MainAxisSize.max,
//                  crossAxisAlignment: CrossAxisAlignment.start,
//                  children: <Widget>[
//                    voteAverageLabel,
//                    Row(
//                      children: <Widget>[
//                        voteAverageWidget,
//                        voteAverageValue,
//                      ],
//                    ),
//                    dateMargin,
//                    dateLabel,
//                    dateWidget,
//                    runtimeMargin,
//                    runtimeLabel,
//                    runtimeWidget,
//                    budgetMargin,
//                    budgetLabel,
//                    budgetWidget,
//                    revenueMargin,
//                    revenueLabel,
//                    revenueWidget,
//                    genresMargin,
//                    genresLabel,
//                    genresWidget,
//                  ],
//                ),
//              ),
            ],
          ),
          biographyMargin,
          biographyLabel,
          biographyWidget,
        ],
      ),
    );

    return details;
  }
}
