import 'package:flutter/material.dart';
import 'package:tmdb/credits/crew_row.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/cast.dart';
import 'package:tmdb/tmdb_api/model/credit.dart';
import 'package:tmdb/tmdb_api/model/crew.dart';

import 'cast_row.dart';

class CreditsTable extends StatelessWidget {
  final CreditsResponse credits;
  final ValueChanged<MovieCast> onCastTap;
  final ValueChanged<MovieCrew> onCrewTap;

  const CreditsTable({
    Key key,
    this.credits,
    this.onCastTap,
    this.onCrewTap,
  })  : assert(credits != null),
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
            string.person_cast_label,
            style: labelStyle,
          )
        : gone;

    final castWidget = hasCast
        ? Card(
            child: Table(
              children: _buildCastList(context, cast),
              columnWidths: {0: IntrinsicColumnWidth()},
            ),
          )
        : gone;

    final hasCrew = (crew != null) && crew.isNotEmpty;

    final crewMargin = hasCast ? SizedBox(height: padding_8) : gone;

    final crewLabel = hasCrew
        ? Text(
            string.person_crew_label,
            style: labelStyle,
          )
        : gone;

    final crewWidget = hasCrew
        ? Card(
            child: Table(
              children: _buildCrewList(context, crew),
              columnWidths: {0: IntrinsicColumnWidth()},
            ),
          )
        : gone;

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        castLabel,
        castWidget,
        crewMargin,
        crewLabel,
        crewWidget,
      ],
    );
  }

  String _getDate(MovieCredit credit) {
    final releaseDate = credit.releaseDate;
    if ((releaseDate != null) && releaseDate.isNotEmpty) {
      return releaseDate;
    }
    final firstAirDate = credit.firstAirDate;
    if ((firstAirDate != null) && firstAirDate.isNotEmpty) {
      return firstAirDate;
    }
    return "9999-99-99";
  }

  List<TableRow> _buildCastList(BuildContext context, List<MovieCast> cast) {
    final list = <TableRow>[];
    cast.sort((a, b) => _getDate(b).compareTo(_getDate(a)));

    final row = CreditsCastRow();

    for (var member in cast) {
      list.add(TableRow(
        children: row.build(context, member, onCastTap),
      ));
    }

    return list;
  }

  List<TableRow> _buildCrewList(BuildContext context, List<MovieCrew> crew) {
    final list = <TableRow>[];
    crew.sort((a, b) => _getDate(b).compareTo(_getDate(a)));

    final row = CreditsCrewRow();

    for (var member in crew) {
      list.add(TableRow(
        children: row.build(context, member, onCrewTap),
      ));
    }

    return list;
  }
}
