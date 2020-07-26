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
    final imageWidth = castDetailsWidth;
    final imageHeight = castDetailsHeight;
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
    );
    final thumbnailWidget = ClipRRect(
      borderRadius: borderCircular_8,
      child: thumbnail,
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Flexible(
      child: Text(
        cast.name,
        maxLines: 2,
        style: textTheme.subtitle1,
        overflow: TextOverflow.ellipsis,
      ),
    );

    final characterWidget = Flexible(
      child: Text(
        cast.character,
        maxLines: 2,
        style: textTheme.subtitle2,
        overflow: TextOverflow.ellipsis,
      ),
    );

    return Card(
      child: InkWell(
        onTap: onCastTap,
        child: Container(
//          color: Colors.green,
          width: imageWidth - 24,
          child: Column(
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
