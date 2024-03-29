import 'package:flutter/material.dart';
import 'package:pinch_zoom/pinch_zoom.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';

class MoviePosterPage extends StatelessWidget {
  final MovieDetails movie;

  const MoviePosterPage({super.key, required this.movie});

  @override
  Widget build(BuildContext context) {
    final poster = TMDBApi.generatePoster(
      posterPath: movie.posterPath,
      posterWidth: double.infinity,
      posterHeight: double.infinity,
      fit: BoxFit.contain,
    );
    final posterWidget = PinchZoom(
      maxScale: 6.0,
      child: poster,
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(movie.getTitle() ?? movie.title),
      ),
      body: Padding(
        padding: paddingAll_8,
        child: posterWidget,
      ),
    );
  }
}
