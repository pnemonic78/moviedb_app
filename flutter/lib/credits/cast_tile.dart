import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/media_cast.dart';
import 'package:tmdb/utils/flutter_ui.dart';

const _titleMaxLines = 2;
const _characterMaxLines = 2;

class CastTile extends StatelessWidget {
  final MediaCast cast;
  final ValueChanged<MediaCast> onTap;

  const CastTile({
    super.key,
    required this.cast,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    onTapCast() => onTap(cast);

    const imageWidth = castTileWidth;
    const imageHeight = castTileHeight;
    final thumbnail = TMDBApi.generateProfile(
      profilePath: cast.profilePath,
      profileWidth: imageWidth,
      profileHeight: imageHeight,
      fit: BoxFit.fitWidth,
    );
    final thumbnailWidget = ClipRRect(
      borderRadius: borderCircularTop_8,
      child: thumbnail,
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      "${cast.name}\n\n",
      maxLines: _titleMaxLines,
      style: textTheme.titleMedium,
      overflow: TextOverflow.ellipsis,
    );

    final characterWidget = Text(
      "${cast.character}\n\n",
      maxLines: _characterMaxLines,
      style: textTheme.titleSmall,
      overflow: TextOverflow.ellipsis,
    );

    return Card(
      child: InkWell(
        onTap: onTapCast,
        child: SizedBox(
          width: imageWidth,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              thumbnailWidget,
              const SizedBox(height: padding_8),
              Padding(padding: paddingHorizontal_8, child: titleWidget),
              const SizedBox(height: padding_4),
              Padding(padding: paddingHorizontal_8, child: characterWidget),
            ],
          ),
        ),
      ),
    );
  }

  double height(BuildContext context) {
    const imageHeight = castTileHeight;
    final textTheme = Theme.of(context).textTheme;
    final titleWidgetHeight =
        textSize("\n\n", textTheme.titleMedium!, _titleMaxLines).height;
    final characterWidgetHeight =
        textSize("\n\n", textTheme.titleSmall!, _characterMaxLines).height;
    return imageHeight +
        padding_8 +
        titleWidgetHeight +
        padding_4 +
        characterWidgetHeight +
        8;
  }
}
