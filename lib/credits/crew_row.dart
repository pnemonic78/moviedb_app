import 'package:flutter/material.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/model/crew.dart';

class CreditsCrewRow {
  List<Widget> build(
      BuildContext context, MovieCrew item, ValueChanged<MovieCrew> onTap) {
    //TODO final onCastTap = onTap == null ? null : () => onTap(cast);

    //TODO final textTheme = Theme.of(context).textTheme;
    final string = AppLocalizations.of(context);

    final date = item.releaseDate ?? item.firstAirDate ?? "-";
    final yearToken = date.split("-")[0];
    final year = yearToken.isEmpty ? "—" : yearToken;
    final yearWidget = Text(year);

    final String title = item.title ?? item.originalTitle ?? item.originalName;
    final String character = item.job ?? "";
    final format = character.isEmpty
        ? string.person_cast_format_none
        : string.person_cast_format;
    final formatted = sprintf(format, [title, character]);

    final jobWidget = Text(
      formatted,
      overflow: TextOverflow.ellipsis,
    );

    return [
      Padding(padding: paddingAll_8, child: yearWidget),
      Padding(padding: paddingAll_8, child: jobWidget),
    ];
  }
}
