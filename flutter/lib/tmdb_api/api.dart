import 'dart:io';
import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';
import 'package:tmdb/tmdb_api/poster_widget.dart';
import 'package:tmdb/tmdb_api/profile_widget.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';
import 'package:video_thumbnail/video_thumbnail.dart';

import 'backdrop_widget.dart';
import 'model/movie.dart';

const Duration fetchTimeout = Duration(seconds: 30);

abstract class TMDBApi {
  static const urlApi = "https://api.themoviedb.org/3/";
  static const _urlImage = "https://image.tmdb.org/t/p/%s%s";
  static const _urlYoutube = "https://www.youtube.com/watch?v=%s";
  static const _urlYoutubeThumbnail = "https://img.youtube.com/vi/%s/0.jpg";
  static const _urlFacebook = "https://facebook.com/%s";
  static const _urlImdb = "https://imdb.com/name/%s";
  static const _urlInstagram = "https://instagram.com/%s";
  static const _urlTwitter = "https://twitter.com/%s";

  static const siteYouTube = "YouTube";

  static const _original = "original";
  static const _backdropSizes = [
    "w300",
    "w780",
    "w1280",
    _original,
  ];

  // static const _logoSizes = [
  //   "w45",
  //   "w92",
  //   "w154",
  //   "w185",
  //   "w300",
  //   "w500",
  //   _original,
  // ];
  static const _posterSizes = [
    "w92",
    "w154",
    "w185",
    "w342",
    "w500",
    "w780",
    _original,
  ];
  static const _profileSizes = [
    "w45",
    "w185",
    "h632",
    _original,
  ];

  // static const _stillSizes = [
  //   "w92",
  //   "w185",
  //   "w300",
  //   _original,
  // ];

  static String? generatePosterUrl(String? path, double width, double height,
      {double devicePixelRatio = 1.0}) {
    if ((path == null) || path.isEmpty || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, _posterSizes);

    return sprintf(_urlImage, [size, path]);
  }

  static String? generateBackdropUrl(String? path, double width, double height,
      {double devicePixelRatio = 1.0}) {
    if ((path == null) || path.isEmpty || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, _backdropSizes);

    return sprintf(_urlImage, [size, path]);
  }

  static String? generateProfileThumbnail(
      String? path, double width, double height,
      {double devicePixelRatio = 1.0}) {
    if ((path == null) || path.isEmpty || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, _profileSizes);

    return sprintf(_urlImage, [size, path]);
  }

  static String findSize(double width, double height, List<String> sizes,
      {double devicePixelRatio = 1.0}) {
    final widthPx =
        (width.isInfinite || width.isNaN) ? width : (width * devicePixelRatio);
    final heightPx = (height.isInfinite || height.isNaN)
        ? height
        : (height * devicePixelRatio);

    String result = _original;
    double minDelta = double.infinity;
    double delta;

    for (var size in sizes) {
      if (size.startsWith("w")) {
        final sizeWidth = double.parse(size.substring(1));
        delta = (widthPx - sizeWidth).abs();
        if (delta < minDelta) {
          minDelta = delta;
          result = size;
        }
      } else if (size.startsWith("h")) {
        final sizeHeight = double.parse(size.substring(1));
        delta = (heightPx - sizeHeight).abs();
        if (delta < minDelta) {
          minDelta = delta;
          result = size;
        }
      }
    }

    return result;
  }

  Future<MoviesResponse> getNowPlaying(BuildContext context);

  Future<MoviesResponse> getPopular(BuildContext context);

  Future<MoviesResponse> getTopRated(BuildContext context);

  Future<MoviesResponse> getUpcoming(BuildContext context);

  Future<CreditsResponse> getMovieCreditsById(
      BuildContext context, int movieId);

  Future<CreditsResponse> getMovieCredits(
      BuildContext context, Movie movie) async {
    return getMovieCreditsById(context, movie.id);
  }

  Future<MovieDetails> getMovieDetailsById(BuildContext context, int movieId);

  Future<MovieDetails> getMovieDetails(
      BuildContext context, Movie movie) async {
    return getMovieDetailsById(context, movie.id);
  }

  Future<VideosResponse> getMovieVideos(
      BuildContext context, Movie movie) async {
    return getMovieVideosById(context, movie.id);
  }

  Future<VideosResponse> getMovieVideosById(BuildContext context, int movieId);

  Future<Person> getPersonById(BuildContext context, int personId);

  Future<Person> getPerson(BuildContext context, Person person) async {
    return getPersonById(context, person.id);
  }

  static Future<Widget?>? generateVideoThumbnail(
      MovieVideo? video, double width, double height) async {
    if ((video == null) || (width <= 0) || (height <= 0)) {
      return null;
    }

    if (video.site == siteYouTube) {
      return _generateYouTubeThumbnail(video.key, width, height);
    }

    var videoUrl = getVideoUrl(video);
    if (videoUrl != null) {
      final temp = await getTemporaryDirectory();
      final path = await VideoThumbnail.thumbnailFile(
        video: videoUrl,
        thumbnailPath: temp.path,
        imageFormat: ImageFormat.JPEG,
        maxHeight: height.toInt(),
        quality: 75,
      );
      if (path == null) return null;
      final file = File(path);
      return Image.file(file, width: width, height: height);
    }
    return null;
  }

  static Future<Widget> _generateYouTubeThumbnail(
      String videoId, double width, double height) async {
    final url = sprintf(_urlYoutubeThumbnail, [videoId]);
    return CachedNetworkImage(
      imageUrl: url,
      placeholder: (context, url) => Icon(
        Icons.movie,
        size: min(width, height),
      ),
      width: width,
      height: height,
    );
  }

  static String? getVideoUrl(MovieVideo? video) {
    if (video == null) {
      return null;
    }
    if (video.site == siteYouTube) {
      return sprintf(_urlYoutube, [video.key]);
    }
    return null;
  }

  static String generateFacebookUrl(String id) {
    return sprintf(_urlFacebook, [id]);
  }

  static String generateImdbUrl(String id) {
    return sprintf(_urlImdb, [id]);
  }

  static String generateInstagramUrl(String id) {
    return sprintf(_urlInstagram, [id]);
  }

  static String generateTwitterUrl(String id) {
    return sprintf(_urlTwitter, [id]);
  }

  static MovieBackdrop generateBackdrop({
    String? backdropPath,
    required double backdropWidth,
    required double backdropHeight,
    BoxFit fit = BoxFit.fitWidth,
  }) {
    return MovieBackdrop(
      backdropPath: backdropPath,
      backdropWidth: backdropWidth,
      backdropHeight: backdropHeight,
      fit: fit,
    );
  }

  static MoviePoster generatePoster({
    String? posterPath,
    required double posterWidth,
    required double posterHeight,
    BoxFit fit = BoxFit.fitHeight,
  }) {
    return MoviePoster(
      posterPath: posterPath,
      posterWidth: posterWidth,
      posterHeight: posterHeight,
      fit: fit,
    );
  }

  static MovieProfile generateProfile({
    String? profilePath,
    required double profileWidth,
    required double profileHeight,
    BoxFit fit = BoxFit.fitHeight,
  }) {
    return MovieProfile(
      profilePath: profilePath,
      profileWidth: profileWidth,
      profileHeight: profileHeight,
      fit: fit,
    );
  }
}
