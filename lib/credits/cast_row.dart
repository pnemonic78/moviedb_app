import 'package:flutter/material.dart';
import 'package:sprintf/sprintf.dart';
import 'package:styled_text/styled_text.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/model/person_cast.dart';

class CreditsCastRow {
  List<Widget> build(
    BuildContext context,
    PersonCast item,
    ValueChanged<PersonCast> onTap,
  ) {
    final onTapItem = onTap == null ? null : () => onTap(item);

    final string = AppLocalizations.of(context);

    final date = item.media.date();
    final year = date?.year?.toString() ?? "—";
    final yearWidget = Text(year);

    final String title = item.title();
    final String character = item.character ?? "";
    final format = character.isEmpty
        ? string.person_cast_format_none
        : string.person_cast_format;
    final formatted = sprintf(format, [title, character]);

    final characterWidget = StyledText(
      text: formatted,
      overflow: TextOverflow.ellipsis,
      styles: {
        'b': TextStyle(fontWeight: FontWeight.bold),
      },
    );

    return [
      TableRowInkWell(
        child: Padding(padding: paddingAll_8, child: yearWidget),
        onTap: onTapItem,
      ),
      TableRowInkWell(
        child: Padding(padding: paddingAll_8, child: characterWidget),
        onTap: onTapItem,
      ),
    ];
  }
}
