import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

import 'api.dart';

class MovieProfile extends StatelessWidget {
  final String? profilePath;
  final double profileWidth;
  final double profileHeight;
  final BoxFit fit;

  const MovieProfile({
    super.key,
    required this.profilePath,
    required this.profileWidth,
    required this.profileHeight,
    this.fit = BoxFit.fitHeight,
  });

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final screenSize = MediaQuery.of(context).size;
    final screenWidth = screenSize.width;
    final screenHeight = screenSize.height;
    final imageWidth = min(profileWidth, screenWidth);
    final imageHeight = min(profileHeight, screenHeight);

    final url = TMDBApi.generateProfileThumbnail(
      profilePath,
      profileWidth,
      profileHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final icon = Icon(
      Icons.person,
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
