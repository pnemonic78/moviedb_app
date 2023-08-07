import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

import 'api.dart';

class MovieBackdrop extends StatelessWidget {
  final String? backdropPath;
  final double backdropWidth;
  final double backdropHeight;
  final BoxFit fit;

  const MovieBackdrop({
    super.key,
    required this.backdropPath,
    required this.backdropWidth,
    required this.backdropHeight,
    this.fit = BoxFit.fitWidth,
  });

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final screenSize = MediaQuery.of(context).size;
    final screenWidth = screenSize.width;
    final screenHeight = screenSize.height;
    final imageWidth = min(backdropWidth, screenWidth);
    final imageHeight = min(backdropHeight, screenHeight);

    final url = TMDBApi.generateBackdropUrl(
      backdropPath,
      backdropWidth,
      backdropHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final icon = Icon(
      Icons.image,
      size: min(imageWidth, imageHeight),
    );
    return (url != null)
        ? CachedNetworkImage(
            imageUrl: url,
            placeholder: (context, url) => icon,
            width: imageWidth,
            height: imageHeight,
            fit: fit,
          )
        : icon;
  }
}
