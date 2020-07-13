import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:pinch_zoom_image_updated/pinch_zoom_image_updated.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';

class MoviePosterPage extends StatelessWidget {
  final String title;
  final String name;
  final String path;

  MoviePosterPage({Key key, this.title, this.name, this.path})
      : assert(path != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    final screenWidth = size.width;
    final screenHeight = size.height;
    final imageWidth = screenWidth;
    final imageHeight = screenHeight;
    final url =
        TMDBApi.generatePosterUrl(this.path, double.infinity, double.infinity);
    final poster = CachedNetworkImage(
      imageUrl: url,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
    );
    final posterWidget = PinchZoomImage(
      image: poster,
    );

    final textTheme = Theme.of(context).textTheme;

    final titleWidget = Text(
      name,
      maxLines: 2,
      style: textTheme.headline5,
      overflow: TextOverflow.ellipsis,
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: Padding(
        padding: paddingAll_8,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            titleWidget,
            Expanded(child: posterWidget),
          ],
        ),
      ),
    );
  }
}
