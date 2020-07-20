import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:pinch_zoom_image_updated/pinch_zoom_image_updated.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';

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
    final posterUrl = TMDBApi.generatePosterUrl(
        movie.posterPath, posterDetailsWidth, posterDetailsHeight);
    final poster = CachedNetworkImage(
      imageUrl: posterUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
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
    );
    final posterWidget = PinchZoomImage(
      image: poster,
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
