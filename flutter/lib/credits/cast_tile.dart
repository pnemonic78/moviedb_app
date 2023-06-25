import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/media_cast.dart';

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
    final onTapCast = () => onTap(cast);

    final imageWidth = castTileWidth;
    final imageHeight = castTileHeight;
    final thumbnail = TMDBApi.generateProfile(
      profilePath: cast.profilePath,
      profileWidth: imageWidth,
      profileHeight: imageHeight,
      fit: BoxFit.fitWidth,
    );
    final thumbnailWidget = ClipPath.shape(
      // rounded rectangle crop for top side only.
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.vertical(top: cardRadius),
      ),
      child: thumbnail,
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      cast.name,
      maxLines: 2,
      style: textTheme.titleMedium,
      overflow: TextOverflow.ellipsis,
    );

    final characterWidget = Text(
      cast.character,
      maxLines: 2,
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
            children: <Widget>[
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
}
