import 'package:flutter/material.dart';
import 'package:tmdb/credits/crew_row.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/model/person_cast.dart';
import 'package:tmdb/tmdb_api/model/person_credit.dart';
import 'package:tmdb/tmdb_api/model/person_crew.dart';
import 'package:tmdb/tmdb_api/person_credits_response.dart';

import 'cast_row.dart';

final _dateFuture = DateTime.parse("9999-99-99");

class CreditsTable extends StatelessWidget {
  final PersonCreditsResponse credits;
  final ValueChanged<PersonCast> onCastTap;
  final ValueChanged<PersonCrew> onCrewTap;

  const CreditsTable({
    super.key,
    required this.credits,
    required this.onCastTap,
    required this.onCrewTap,
  });

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    final labelStyle = textTheme.titleLarge;
    final gone = Container();
    final string = AppLocalizations.get(context);

    final cast = credits.cast;
    final crew = credits.crew;

    final hasCast = cast.isNotEmpty;

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
              columnWidths: const {0: IntrinsicColumnWidth()},
            ),
          )
        : gone;

    final hasCrew = crew.isNotEmpty;

    final crewMargin = hasCast ? const SizedBox(height: padding_8) : gone;

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
              columnWidths: const {0: IntrinsicColumnWidth()},
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

  DateTime _getDate(PersonCredit credit) {
    return credit.media.date() ?? _dateFuture;
  }

  List<TableRow> _buildCastList(BuildContext context, List<PersonCast> cast) {
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

  List<TableRow> _buildCrewList(BuildContext context, List<PersonCrew> crew) {
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
