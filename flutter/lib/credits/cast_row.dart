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
    final onTapItem = () => onTap(item);

    final string = AppLocalizations.get(context);

    final date = item.media.date();
    final year = date?.year.toString() ?? "—";
    final yearWidget = Text(year);

    final String title = item.title() ?? "";
    final String character = item.character ?? "";
    final format = character.isEmpty
        ? string.person_cast_format_none
        : string.person_cast_format;
    final formatted = sprintf(format, [title, character]);

    final characterWidget = StyledText(
      text: formatted,
      overflow: TextOverflow.ellipsis,
      tags: {
        'b': StyledTextTag(style: const TextStyle(fontWeight: FontWeight.bold)),
      },
    );

    return [
      TableRowInkWell(
        onTap: onTapItem,
        child: Padding(padding: paddingAll_8, child: yearWidget),
      ),
      TableRowInkWell(
        onTap: onTapItem,
        child: Padding(padding: paddingAll_8, child: characterWidget),
      ),
    ];
  }
}
