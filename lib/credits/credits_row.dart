import 'package:flutter/material.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/model/cast.dart';

const tileHeight = 50.0;

class CreditsCastRow extends TableRow {
  final MovieCast cast;
  final ValueChanged<MovieCast> onTap;

  const CreditsCastRow({
    Key key,
    @required this.cast,
    @required this.onTap,
  })  : assert(cast != null),
        super(key: key);

  List<Widget> build(BuildContext context) {
    //TODO final onCastTap = onTap == null ? null : () => onTap(cast);

    //TODO final textTheme = Theme.of(context).textTheme;
    final string = AppLocalizations.of(context);

    final date = cast.releaseDate ?? cast.firstAirDate ?? "-";
    final yearToken = date.split("-")[0];
    final year = yearToken.isEmpty ? "—" : yearToken;
    final yearWidget = Text(year);

    final String title =
        cast.title ?? cast.originalTitle ?? cast.originalName ?? "TITLE";
    final String character = cast.character ?? "";
    final format = character.isEmpty
        ? string.person_cast_format_none
        : string.person_cast_format;
    final formatted = sprintf(format, [title, character]);

    final characterWidget = Text(
      formatted,
      overflow: TextOverflow.ellipsis,
    );

    return [
      yearWidget,
      characterWidget,
    ];
  }
}
