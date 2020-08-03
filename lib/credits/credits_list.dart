import 'package:flutter/material.dart';
import 'package:tmdb/credits/cast_tile.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/cast.dart';
import 'package:tmdb/tmdb_api/model/crew.dart';

class CreditsList extends StatelessWidget {
  final CreditsResponse credits;

  const CreditsList({Key key, this.credits})
      : assert(credits != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    final labelStyle = textTheme.headline6;
    final gone = Container();
    final string = AppLocalizations.of(context);

    final cast = credits.cast;
    final crew = credits.crew;

    final hasCast = (cast != null) && cast.isNotEmpty;

    final castLabel = hasCast
        ? Text(
            string.known_for_label,
            style: labelStyle,
          )
        : gone;

    final castWidget = hasCast
        ? ListView(
            children: _buildCastList(context, cast),
          )
        : gone;

    final hasCrew = (crew != null) && crew.isNotEmpty;

    final crewMargin = hasCast ? SizedBox(height: padding_8) : gone;

    final crewLabel = hasCrew
        ? Text(
            string.known_for_label,
            style: labelStyle,
          )
        : gone;

    final crewWidget = hasCrew
        ? ListView(
            children: _buildCrewList(context, crew),
          )
        : gone;

    return Column(
      children: [
        castLabel,
        castWidget,
        crewMargin,
        crewLabel,
        crewWidget,
      ],
    );
  }

  List<Widget> _buildCastList(BuildContext context, List<MovieCast> cast) {
    final list = <Widget>[];
    cast.sort((a, b) => a.releaseDate.compareTo(b.releaseDate));

    for (var member in cast) {
      list.add(CastTile(
        cast: member,
        //TODO onTap: onTap,
      ));
    }

    return list;
  }

  List<Widget> _buildCrewList(BuildContext context, List<MovieCrew> crew) {
    final list = <Widget>[];
    //TODO crew.sort((a, b) => a.releaseDate.compareTo(b.releaseDate));

    for (var member in crew) {
      //TODO add row tile.
    }

    return list;
  }
}
