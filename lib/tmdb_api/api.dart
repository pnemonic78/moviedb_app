import 'dart:io';
import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:inject/inject.dart';
import 'package:path_provider/path_provider.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/keys.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';
import 'package:video_thumbnail/video_thumbnail.dart';

import 'model/movie.dart';
import 'movies_response.dart';
import 'rest_client.dart';

@provide
@singleton
// `flutter pub run build_runner build`
class TMDBApi {
  static const api_url = "https://api.themoviedb.org/3/";
  static const image_url = "https://image.tmdb.org/t/p/%s%s";
  static const youtube_url = "https://www.youtube.com/watch?v=%s";
  static const youtube_thumbnail = "https://img.youtube.com/vi/%s/0.jpg";
  static const facebook_url = "https://facebook.com/%s";
  static const imdb_url = "https://imdb.com/name/%s";
  static const instagram_url = "https://instagram.com/%s";
  static const twitter_url = "https://twitter.com/%s";
  static const _apiKey = Keys.apiKey;

  static const _original = "original";
  static const backdrop_sizes = [
    "w300",
    "w780",
    "w1280",
    _original,
  ];
  static const logo_sizes = [
    "w45",
    "w92",
    "w154",
    "w185",
    "w300",
    "w500",
    _original,
  ];
  static const poster_sizes = [
    "w92",
    "w154",
    "w185",
    "w342",
    "w500",
    "w780",
    _original,
  ];
  static const profile_sizes = [
    "w45",
    "w185",
    "h632",
    _original,
  ];
  static const still_sizes = [
    "w92",
    "w185",
    "w300",
    _original,
  ];

  static String generatePosterUrl(String path, double width, double height,
      {double devicePixelRatio = 1.0}) {
    if ((path == null) || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, poster_sizes);

    return sprintf(image_url, [size, path]);
  }

  static String generateBackdropUrl(String path, double width, double height,
      {double devicePixelRatio = 1.0}) {
    if ((path == null) || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, backdrop_sizes);

    return sprintf(image_url, [size, path]);
  }

  static String generateProfileThumbnail(
      String path, double width, double height,
      {double devicePixelRatio = 1.0}) {
    if ((path == null) || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, profile_sizes);

    return sprintf(image_url, [size, path]);
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

  RestClient _client;

  TMDBApi() {
    final dio = Dio();
    _client = RestClient(dio, baseUrl: api_url);
  }

  Future<MoviesResponse> getNowPlaying(BuildContext context) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMoviesNowPlaying(
      apiKey: _apiKey,
      language: locale.languageCode,
    );
  }

  Future<CreditsResponse> getMovieCreditsById(
      BuildContext context, int movieId) async {
    return _client.getMovieCredits(
      apiKey: _apiKey,
      moveId: movieId,
    );
  }

  Future<CreditsResponse> getMovieCredits(
      BuildContext context, Movie movie) async {
    return getMovieCreditsById(context, movie.id);
  }

  Future<MovieDetails> getMovieDetailsById(
      BuildContext context, int movieId) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getMovieDetails(
      apiKey: _apiKey,
      moveId: movieId,
      language: locale.languageCode,
      append: "credits",
    );
  }

  Future<MovieDetails> getMovieDetails(
      BuildContext context, Movie movie) async {
    return getMovieDetailsById(context, movie.id);
  }

  Future<VideosResponse> getMovieVideos(
      BuildContext context, Movie movie) async {
    return _client.getMovieVideos(
      apiKey: _apiKey,
      moveId: movie.id,
    );
  }

  Future<Person> getPersonById(BuildContext context, int personId) async {
    final Locale locale = Localizations.localeOf(context);
    return _client.getPerson(
      apiKey: _apiKey,
      personId: personId,
      language: locale.languageCode,
      append: "external_ids,combined_credits",
    );
  }

  Future<Person> getPerson(BuildContext context, Person person) async {
    return getPersonById(context, person.id);
  }

  static Future<Widget> generateVideoThumbnail(
      MovieVideo video, double width, double height) async {
    if ((video == null) || (width <= 0) || (height <= 0)) {
      return null;
    }

    if (video.site == "YouTube") {
      return _generateYouTubeThumbnail(video.key, width, height);
    }

    var videoUrl;
    if (videoUrl != null) {
      final temp = await getTemporaryDirectory();
      final path = await VideoThumbnail.thumbnailFile(
        video: videoUrl,
        thumbnailPath: temp.path,
        imageFormat: ImageFormat.JPEG,
        maxHeight: height.toInt(),
        quality: 75,
      );
      final file = File(path);
      return Image.file(file, width: width, height: height);
    }
    return null;
  }

  static Future<Widget> _generateYouTubeThumbnail(
      String videoId, double width, double height) async {
    final url = sprintf(youtube_thumbnail, [videoId]);
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

  static String getVideoUrl(MovieVideo video) {
    if (video == null) {
      return null;
    }
    if (video.site == "YouTube") {
      return sprintf(youtube_url, [video.key]);
    }
    return null;
  }
}
