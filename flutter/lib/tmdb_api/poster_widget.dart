import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

import 'api.dart';

class MoviePoster extends StatelessWidget {
  final String? posterPath;
  final double posterWidth;
  final double posterHeight;
  final BoxFit fit;

  const MoviePoster({
    super.key,
    required this.posterPath,
    required this.posterWidth,
    required this.posterHeight,
    this.fit = BoxFit.fitHeight,
  });

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final screenSize = MediaQuery.of(context).size;
    final screenWidth = screenSize.width;
    final screenHeight = screenSize.height;
    final imageWidth = min(posterWidth, screenWidth);
    final imageHeight = min(posterHeight, screenHeight);

    final url = TMDBApi.generatePosterUrl(
      posterPath,
      posterWidth,
      posterHeight,
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
