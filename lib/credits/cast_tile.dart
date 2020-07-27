import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/cast.dart';

class CastTile extends StatelessWidget {
  final MovieCast cast;
  final ValueChanged<MovieCast> onTap;

  const CastTile({
    Key key,
    @required this.cast,
    @required this.onTap,
  })  : assert(cast != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final onCastTap = onTap == null ? null : () => onTap(cast);

    final media = MediaQuery.of(context);
    final imageWidth = castTileWidth;
    final imageHeight = castTileHeight;
    final thumbnailUrl = TMDBApi.generateProfileThumbnail(
      cast.profilePath,
      imageWidth,
      imageHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final thumbnail = CachedNetworkImage(
      imageUrl: thumbnailUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
      fit: BoxFit.fitWidth,
    );
    final thumbnailWidget = ClipPath.shape(
      // rounded rectangle crop for top side only.
      shape: RoundedRectangleBorder(
        side: BorderSide(color: Colors.blue),
        borderRadius: BorderRadius.vertical(top: Radius.circular(8.0)),
      ),
      child: thumbnail,
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      cast.name,
      maxLines: 2,
      style: textTheme.subtitle1,
      overflow: TextOverflow.ellipsis,
    );

    final characterWidget = Text(
      cast.character,
      maxLines: 2,
      style: textTheme.subtitle2,
      overflow: TextOverflow.ellipsis,
    );

    return Card(
      child: InkWell(
        onTap: onCastTap,
        child: Container(
          width: imageWidth,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              thumbnailWidget,
              SizedBox(height: padding_8),
              Padding(padding: paddingHorizontal_8, child: titleWidget),
              SizedBox(height: padding_4),
              Padding(padding: paddingHorizontal_8, child: characterWidget),
            ],
          ),
        ),
      ),
    );
  }
}
