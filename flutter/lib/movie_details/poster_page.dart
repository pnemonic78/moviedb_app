import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:pinch_zoom/pinch_zoom.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';

class MoviePosterPage extends StatelessWidget {
  final MovieDetails movie;

  MoviePosterPage({Key key, this.movie})
      : assert(movie != null),
        super(key: key);

  // The small poster that should be cached from the details page.
  Widget _posterSmall(
    BuildContext context,
    double imageWidth,
    double imageHeight,
  ) {
    final media = MediaQuery.of(context);
    final posterUrl = TMDBApi.generatePosterUrl(
      movie.posterPath,
      posterDetailsWidth,
      posterDetailsHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final poster = CachedNetworkImage(
      imageUrl: posterUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
      fit: BoxFit.contain,
    );
    return poster;
  }

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    final screenWidth = size.width;
    final screenHeight = size.height;
    final imageWidth = screenWidth;
    final imageHeight = screenHeight;
    final imagePath = movie.posterPath;
    final imageUrl =
        TMDBApi.generatePosterUrl(imagePath, double.infinity, double.infinity);
    final poster = CachedNetworkImage(
      imageUrl: imageUrl,
      placeholder: (context, url) =>
          _posterSmall(context, imageWidth, imageHeight),
      width: imageWidth,
      height: imageHeight,
      fit: BoxFit.contain,
    );
    final posterWidget = PinchZoom(
      child: poster,
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(movie.title),
      ),
      body: Padding(
        padding: paddingAll_8,
        child: posterWidget,
      ),
    );
  }
}
