import 'package:flutter/material.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/model/person_crew.dart';

class CreditsCrewRow {
  List<Widget> build(
    BuildContext context,
    PersonCrew item,
    ValueChanged<PersonCrew> onTap,
  ) {
    final onItemTap = onTap == null ? null : () => onTap(item);

    //TODO final textTheme = Theme.of(context).textTheme;
    final string = AppLocalizations.of(context);

    final date = item.media.date();
    final year = date?.year?.toString() ?? "—";
    final yearWidget = Text(year);

    final String title = item.title();
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
      TableRowInkWell(
        child: Padding(padding: paddingAll_8, child: yearWidget),
        onTap: onItemTap,
      ),
      TableRowInkWell(
        child: Padding(padding: paddingAll_8, child: jobWidget),
        onTap: onItemTap,
      ),
    ];
  }
}
